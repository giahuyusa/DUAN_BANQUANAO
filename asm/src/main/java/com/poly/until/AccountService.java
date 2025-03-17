package com.poly.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.poly.Dao.AccountDAO;
import com.poly.model.Accounts;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private JavaMailSender mailSender;

    // Lấy danh sách tài khoản có phân trang
    public Page<Accounts> getAllUsers(int page, int size) {
        return accountDAO.findAll(PageRequest.of(page, size));
    }

    // Đăng ký tài khoản mới
    public void register(Accounts user) {
        accountDAO.save(user);
    }

    // Tìm tài khoản theo ID
    public Accounts getUserById(String id) {
        Optional<Accounts> user = accountDAO.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản!"));
    }

    // Cập nhật tài khoản
    public void updateUser(String id, Accounts updatedUser) {
        if (accountDAO.existsById(id)) {
            updatedUser.setUsername(id); // Đảm bảo ID không thay đổi
            accountDAO.save(updatedUser);
        } else {
            throw new RuntimeException("Không tìm thấy tài khoản!");
        }
    }

    // Xóa tài khoản theo ID
    public void deleteUser(String id) {
        if (accountDAO.existsById(id)) {
            accountDAO.deleteById(id);
        } else {
            throw new RuntimeException("Không tìm thấy tài khoản!");
        }
    }

    // Xử lý quên mật khẩu và gửi email
    public String forgotPassword(String email) {
        Accounts account = accountDAO.findByEmail(email);

        if (account != null) {
            // Gửi email chứa mật khẩu cũ
            sendEmail(account.getEmail(), "Quên mật khẩu", 
                      "Mật khẩu cũ của bạn là: " + account.getPassword());
            return "success";
        } else {
            return "error";
        }
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
