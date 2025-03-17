package com.poly.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Categories;
import com.poly.model.product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Lấy danh sách tất cả sản phẩm
    public List<product> getAllProducts() {
        return productRepository.findAll();
    }

    // Tìm sản phẩm theo ID
    public product findById(Long id) {
        Optional<product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Lưu sản phẩm (dùng để thêm mới hoặc cập nhật)
    public void saveProduct(product product) {
        productRepository.save(product);
    }

    // Xóa sản phẩm theo ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // Reset database (xóa toàn bộ sản phẩm)
    public void resetDatabase() {
        productRepository.deleteAll();
    }
    @Autowired
    private CategoryRepository categoryRepository;
    public Categories findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
