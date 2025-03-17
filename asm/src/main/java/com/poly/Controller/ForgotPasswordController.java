
package com.poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.until.AccountService;

@Controller

public class ForgotPasswordController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/forgot-password")
    public String showForgotPasswordPage() {
        return "forget-pass"; 
    }
    
    @PostMapping("/account/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        String result = accountService.forgotPassword(email);

        if (result.equals("success")) {
            model.addAttribute("successMessage", "Mật khẩu đã được gửi qua email!");
        } else {
            model.addAttribute("errorMessage", "Email không tồn tại trong hệ thống!");
        }

        return "forget-pass"; 
    }
}