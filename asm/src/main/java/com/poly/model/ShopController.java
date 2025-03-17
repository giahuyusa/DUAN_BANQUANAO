package com.poly.model;

import com.poly.until.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shop/index")
    public String shop(Model model) {
        List<product> products = productRepository.findByIsDeleteFalse();
        model.addAttribute("products", products);
        return "productshop";
    }
}
