package com.poly.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.Service.SessionService;
import com.poly.model.Accounts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	Accounts user = sessionService.get("user"); // Lấy user từ session
        String uri = request.getRequestURI();

        System.out.println("Interceptor kiểm tra: " + uri);
        System.out.println("User từ session: " + user);

        // Nếu chưa đăng nhập
        if (user == null) {
        	System.out.println("chua dang nhap");
            sessionService.set("secureUri", uri);
            response.sendRedirect("/auth/login?error=Please log in");
            return false;
        }

        // Nếu không phải admin mà vào trang admin
        if (!user.isAdmin() && uri.startsWith("/admin")) {
            response.sendRedirect("/auth/login?error=Access denied");
            return false;
        }

        return true; // Cho phép truy cập
    }
}
