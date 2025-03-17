package com.poly.Controller;

import com.poly.model.Categories;
import com.poly.until.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String getCategories(Model model) {
        List<Categories> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return  "quantri"; // Tên file Thymeleaf hiển thị danh sách
    }
    @GetMapping("/list/json")
    @ResponseBody
    public List<Categories> getCategoriesJson() {
        return categoryService.findAll();
    }

    @PostMapping("/save")
    public String addCategory(@RequestParam String name, RedirectAttributes redirectAttributes) {
        try {
            Categories category = new Categories();
            category.setName(name);
            categoryService.save(category);
            
            redirectAttributes.addFlashAttribute("successMessage", "Thêm loại sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thêm loại sản phẩm thất bại!");
        }
        return "redirect:/admin/product/index";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa loại sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa loại sản phẩm này!");
        }
        return "redirect:/admin/product/index";
    }


}
