package com.poly.Controller;

import com.poly.model.Categories;
import com.poly.model.product;
import com.poly.until.CategoryRepository;
import com.poly.until.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin/product/index")
public class AdminController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryRepository categoryRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    // Lưu sản phẩm (bao gồm upload ảnh)
    @PostMapping("/uploadImage")
    public String saveProduct(
            @ModelAttribute("product") product product,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        // Lấy category từ database
        Categories category = productService.findCategoryById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category không tồn tại!");
        }
        product.setCategory(category);

        // Nếu có file ảnh mới được tải lên
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(filePath.getParent());
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                product.setImage("/images/" + fileName); // Lưu đường dẫn ảnh

            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi tải ảnh lên!", e);
            }
        }

        // Lưu sản phẩm vào database
        productService.saveProduct(product);
        return "redirect:/admin/product/index";
    }

    // Danh sách sản phẩm
    @GetMapping("")
    public String getProductList(Model model) {
        List<product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        List<Categories> categories = categoryRepository.findAll();
        model.addAttribute("listCategorie", categories);
        return "quantri";
    }

    // Hiển thị form thêm/sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        product product = productService.findById(id);
        model.addAttribute("product", product);
        return "quantri"; 
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") product product, @RequestParam("categoryId") Long categoryId) {
        Categories category = productService.findCategoryById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category không tồn tại!");
        }

        product.setCategory(category);

        productService.saveProduct(product);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product/index";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Model model, Categories categories) {
        categoryRepository.save(categories);
        return "redirect:/admin/product/index";
    }
}
