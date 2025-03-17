package com.poly.Service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        return Optional.ofNullable(cookies)
                       .flatMap(cs -> Arrays.stream(cs)
                                            .filter(cookie -> cookie.getName().equals(name))
                                            .findFirst())
                       .orElse(null);
    }
	
	public String getValue(String name) {
        Cookie[] cookies = request.getCookies();
        return Optional.ofNullable(cookies)
                       .flatMap(cs -> Arrays.stream(cs)
                                            .filter(cookie -> cookie.getName().equals(name))
                                            .findFirst()
                                            .map(Cookie::getValue))
                       .orElse(null);
    }
	
	public Cookie add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours*60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
	
	public void remove(String name) {
        Cookie[] cookies = request.getCookies();
        Optional.ofNullable(cookies)
                .flatMap(cs -> Arrays.stream(cs)
                                     .filter(cookie -> cookie.getName().equals(name))
                                     .findFirst())
                .ifPresent(cookie -> {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                });
    }
}
