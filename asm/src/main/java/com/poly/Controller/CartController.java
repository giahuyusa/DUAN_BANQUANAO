package com.poly.Controller;

import com.poly.model.Accounts;
import com.poly.model.CartItem;
import com.poly.model.OrderDetails;
import com.poly.model.Orders;
import com.poly.model.product;
import com.poly.until.OrderDetailsRepository;
import com.poly.until.OrdersRepository;
import com.poly.until.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalQuantity", cartService.getTotalQuantity());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            cartService.addToCart(product, 1);
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam("productId") Long productId,
                             @RequestParam("quantity") int quantity) {
        cartService.updateCartItem(productId, Math.max(quantity, 1));
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id) {
        cartService.removeCartItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/total")
    public String calculateTotal(@RequestParam(value = "selectedProducts", required = false) List<Long> selectedIds,
                                 Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        double totalAmount = (selectedIds != null) ? cartItems.stream()
            .filter(item -> selectedIds.contains(item.getProduct().getId()))
            .mapToDouble(CartItem::getTotalPrice)
            .sum() : 0;

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("selectedIds", selectedIds);

        return "cart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("username") String username,
                           @RequestParam("address") String address,
                           Model model,
                           HttpSession session) {

        Accounts user = (Accounts) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Bạn cần đăng nhập để thanh toán!");
            return "login";
        }

        if (cartService.getCartItems().isEmpty()) {
            model.addAttribute("error", "Giỏ hàng trống, không thể thanh toán!");
            return "cart";
        }

        Orders order = new Orders();
        order.setUsername(user.getUsername());
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setAddress(address);
        order.setTotal(cartService.getTotalAmount());
        order.setStatus(0);

        Orders savedOrder = orderRepository.save(order);

        String productNames = cartService.getCartItems().stream()
                .map(item -> item.getProduct().getName())
                .collect(Collectors.joining(", "));

        String productQuantities = cartService.getCartItems().stream()
                .map(item -> String.valueOf(item.getQuantity()))
                .collect(Collectors.joining(", "));

        for (CartItem item : cartService.getCartItems()) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrderId(savedOrder.getId());
            orderDetail.setProductId(item.getProduct().getId());
            orderDetail.setPrice(item.getProduct().getPrice());
            orderDetail.setQuantity(item.getQuantity());
            orderDetailsRepository.save(orderDetail);
        }

        cartService.clearCart();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedTotal = currencyFormat.format(savedOrder.getTotal());

        String subject = "Xác nhận đơn hàng #" + savedOrder.getId();
        String message = "Cảm ơn bạn đã đặt hàng!\n\n" +
                         "Tên tài khoản: " + user.getUsername() + "\n" +
                         "Địa chỉ: " + address + "\n" +
                         "Sản phẩm: " + productNames + "\n" +
                         "Số lượng: " + productQuantities + "\n" +
                         "Tổng tiền: " + formattedTotal + "\n\n" +
                         "Chúng tôi sẽ xử lý đơn hàng trong thời gian sớm nhất.";

        emailService.sendEmail(user.getEmail(), subject, message);

        model.addAttribute("order_success", true);
        return "cart";
    }
}
