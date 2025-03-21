package com.poly.until;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
}
