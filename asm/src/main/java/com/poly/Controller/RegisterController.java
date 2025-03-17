package com.poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.model.Accounts;
import com.poly.until.AccountRepository;

@Controller

public class RegisterController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/account/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new Accounts()); 
        return "register";
    }

    @PostMapping("/account/register")
    public String registerUser(@ModelAttribute("registerForm") Accounts acc, BindingResult result, Model model) {

        if (accountRepository.existsById(acc.getUsername())) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
            return "register";
        }
        

        try {
            acc.setPhoto(""); // Nếu không có ảnh, đặt mặc định
            accountRepository.save(acc);
            return "redirect:/auth/login"; // Đăng ký thành công -> Chuyển sang login
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Đăng ký thất bại. Vui lòng thử lại!");
            return "register";
        }
    }
}
