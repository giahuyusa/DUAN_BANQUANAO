package com.poly.Controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.Dao.AccountDAO;
import com.poly.model.Accounts;
import com.poly.until.AccountRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {

    @Autowired
    private AccountDAO accountDAO;

    // Hiển thị form đăng nhập
    @GetMapping("/auth/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new Accounts());
        return "login";
    }

    @PostMapping("/auth/login")
    public String loginUser(@ModelAttribute("loginForm") Accounts loginForm, Model model, HttpSession session) {
        Accounts account = accountDAO.findByUsername(loginForm.getUsername());

        if (account != null && account.getPassword().equals(loginForm.getPassword())) {
            session.setAttribute("user", account);  

            if (account.isAdmin()) {
                return "redirect:/admin/product/index"; 
            } else {
                return "redirect:/home/index"; 
            }
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            return "login"; 
        }
    }

    @Autowired
    private AccountRepository accountRepo; 

    // Cập nhật thông tin tài khoản (bao gồm ảnh)
    @PostMapping("update-account")
    public String updateAccount(
    		 @RequestParam("fullname") String fullname,
    		    @RequestParam("password") String password,
    		    @RequestParam("photo") MultipartFile imageFile,
    		    HttpSession session,
    		    HttpServletRequest request,
    		    RedirectAttributes redirectAttributes) {

    		    Accounts user = (Accounts) session.getAttribute("user");

    		    if (user != null) {
    		        user.setFullname(fullname);

    		        // Cập nhật mật khẩu nếu không trống
    		        if (password != null && !password.trim().isEmpty()) {
    		            user.setPassword(password);
    		        }

    		        // Xử lý upload ảnh nếu có file được chọn
    		        if (!imageFile.isEmpty()) {
    		            try {
    		                // Đường dẫn lưu file
    		                String uploadDir = request.getServletContext().getRealPath("/image/");
    		                File uploadFolder = new File(uploadDir);
    		                if (!uploadFolder.exists()) {
    		                    uploadFolder.mkdirs(); // Tạo thư mục nếu chưa có
    		                }

    		                // Đặt tên file duy nhất
    		                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
    		                File saveFile = new File(uploadDir, fileName);
    		                imageFile.transferTo(saveFile);

    		                // Cập nhật tên ảnh vào tài khoản
    		                user.setPhoto(fileName);
    		            } catch (IOException e) {
    		                e.printStackTrace();
    		                redirectAttributes.addFlashAttribute("error", "Lỗi khi tải ảnh!");
    		                return "redirect:/home/index";
    		            }
    		        }

    		        accountRepo.save(user);
    		        session.setAttribute("user", user);
    		        redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
    		    } else {
    		        redirectAttributes.addFlashAttribute("error", "Bạn chưa đăng nhập!");
    		        return "redirect:/auth/login";
    		    }

    		    return "redirect:/home/index";
    		}

    // Xử lý đăng xuất
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); 
        return "redirect:/auth/login"; 
    }
    
}
