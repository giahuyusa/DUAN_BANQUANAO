package com.poly.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
    @Autowired
    private HttpServletRequest request;

    private Optional<String> getParam(String name) {
        return Optional.ofNullable(request.getParameter(name)).map(String::trim);
    }

    public String getString(String name, String defaultValue) {
        return getParam(name).filter(s -> !s.isEmpty()).orElse(defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        return getParam(name)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .orElse(defaultValue);
    }

    public double getDouble(String name, double defaultValue) {
        return getParam(name)
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .orElse(defaultValue);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return getParam(name)
                .filter(s -> !s.isEmpty())
                .map(Boolean::parseBoolean)
                .orElse(defaultValue);
    }

    public Date getDate(String name, String pattern) {
        return getParam(name)
                .filter(s -> !s.isEmpty())
                .map(value -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    return Date.from(LocalDateTime.parse(value, formatter)
                            .atZone(ZoneId.systemDefault())
                            .toInstant());
                }).orElse(null);
    }

    public File saveFile(MultipartFile file, String path) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            Path destPath = Paths.get(path, file.getOriginalFilename());
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destPath, StandardCopyOption.REPLACE_EXISTING);
            }
            return destPath.toFile();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu tệp: " + file.getOriginalFilename(), e);
        }
    }
}
