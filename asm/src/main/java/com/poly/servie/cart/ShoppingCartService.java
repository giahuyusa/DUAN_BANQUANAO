package com.poly.servie.cart;



import java.util.Collection;

import com.poly.model.CartItem;

public interface ShoppingCartService {
    // Add a CartItem to the cart by product ID
    CartItem add(Long productId);

    // Remove a CartItem by its ID
    void remove(Long productId);

    // Update quantity of a CartItem and return updated item
    CartItem update(Long productId, int qty);

    // Clear all items in the cart
    void clear();

    // Get all items in the cart
    Collection<CartItem> getItems();

    // Get the total count of items in the cart
    int getCount();

    // Get the total price of all items in the cart
    double getAmount();
}
