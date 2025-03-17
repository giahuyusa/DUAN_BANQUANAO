package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;
    private product product;
    private int quantity;
    private double totalPrice;
    
    
    public double calculateTotalPrice() {
        return product.getPrice() * quantity;
    }
}