package com.poly.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    /**
     * Đọc giá trị của attribute trong session
     * @param name tên attribute
     * @return giá trị đọc được hoặc null nếu không tồn tại
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return Optional.ofNullable(session)
                       .map(s -> (T) s.getAttribute(name))
                       .orElse(null);
    }

    /**
     * Đọc giá trị của attribute trong session
     * @param name tên attribute
     * @return giá trị đọc được hoặc null nếu không tồn tại
     */
    public String getValue(String name) {
        return Optional.ofNullable(session)
                       .map(s -> (String) s.getAttribute(name))
                       .orElse(null);
    }

    /**
     * Thay đổi hoặc tạo mới attribute trong session
     * @param name tên attribute
     * @param value giá trị attribute
     */
    public void set(String name, Object value) {
        Optional.ofNullable(session)
                .ifPresent(s -> s.setAttribute(name, value));
    }

    /**
     * Xóa attribute trong session
     * @param name tên attribute cần xóa
     */
    public void remove(String name) {
        Optional.ofNullable(session)
                .ifPresent(s -> s.removeAttribute(name));
    }
}
