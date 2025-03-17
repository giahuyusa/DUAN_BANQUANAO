package com.poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.model.Accounts;
import com.poly.until.AccountService;

@Controller

@RequestMapping("/admin/account/index")
public class UserController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String userManager(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Accounts> userPage = accountService.getAllUsers(page, 8);
        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("mode", "list");
        return "createUser";
    }

    @GetMapping("/create")
    public String showCreateUser(Model model) {
        model.addAttribute("user", new Accounts());
        model.addAttribute("mode", "create");
        return "createUser";
    }

    @PostMapping("/create")
    public String userInsert(@ModelAttribute("user") Accounts user, RedirectAttributes redirectAttributes) {
        try {
            accountService.register(user);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo tài khoản thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/admin/account/index ";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", accountService.getUserById(id));
        model.addAttribute("mode", "update");
        return "createUser";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id, @ModelAttribute("user") Accounts updatedUser, RedirectAttributes redirectAttributes) {
        accountService.updateUser(id, updatedUser);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công");
        return "redirect:/admin/account/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        accountService.deleteUser(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa thành công");
        return "redirect:/admin/account/index";
    }
}
