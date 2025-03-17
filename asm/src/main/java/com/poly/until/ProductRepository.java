package com.poly.until;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.*;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<product, Long> {
    List<product> findByIsDeleteFalse(); 
    
}

