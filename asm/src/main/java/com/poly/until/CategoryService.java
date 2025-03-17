package com.poly.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Categories;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categories> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Categories category) {
        categoryRepository.save(category);
    }

 
    
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

