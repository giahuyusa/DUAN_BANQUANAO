package com.poly.Controller;

import com.poly.model.Orders;
import com.poly.until.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderAdminController {
    
    @Autowired
    private OrderService orderService;

   
    
    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Orders order = orderService.getOrderById(id);
        if (order == null) {
            return "redirect:/admin/orders";
        }
        model.addAttribute("order", order);
        return "order_management";
    }
    
    @GetMapping
    public String listOrders(Model model, @RequestParam(value = "success", required = false) String success) {
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        if (success != null) {
            model.addAttribute("message", "Cập nhật trạng thái thành công!");
        }
        return "order_management";
    }
    
    @PostMapping("/update-status")
    public String updateOrderStatus(@RequestParam Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/admin/orders?success=true";
    }
}
