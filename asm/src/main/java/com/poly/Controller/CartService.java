package com.poly.Controller;

import org.springframework.stereotype.Service;

import com.poly.model.CartItem;
import com.poly.model.product;

import java.util.ArrayList;
import java.util.List;

@Service

public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                item.setTotalPrice(item.calculateTotalPrice());
                return;
            }
        }
        CartItem newItem = new CartItem((long) (cartItems.size() + 1), product, quantity, product.getPrice() * quantity);
        cartItems.add(newItem);
    }
    public void updateCartItem(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                item.setTotalPrice(item.calculateTotalPrice());
                return;
            }
        }
    }

	/*
	 * Thêm phương thức removeCartItem để xóa sản phẩm khỏi giỏ hàng:
	 */    public void removeCartItem(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

	// Tính tổng tiền giỏ hàng
	 public double getTotalAmount() {
	     return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
	 }

	 // Xóa giỏ hàng sau khi thanh toán
	 public void clearCart() {
	     cartItems.clear();
	 }
//phương thức để lấy tổng số lượng sản phẩm hiện có trong giỏ hàng:
	 public int getTotalQuantity() {
		    return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
		}
}

