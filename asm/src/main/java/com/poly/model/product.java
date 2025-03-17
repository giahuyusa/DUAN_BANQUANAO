package com.poly.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(nullable = false)
    private String description;

    private String image;
    private String image1; // Đường dẫn hình ảnh phụ 1
    private String image2; // Đường dẫn hình ảnh phụ 2
    private String image3; // Đường dẫn hình ảnh phụ 3
    private String image4; // Đường dẫn hình ảnh phụ 4
    
    @Column(nullable = false)
    private Double price;

    @Builder.Default
    private Date createDate = new Date();

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isDelete = false;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference 
    private Categories category;


    public product(String image, String name, Double price) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.createDate = new Date();
        this.quantity = 10;
        this.isDelete = false;
    }

    public product(String name, String image, Double price, Integer quantity, Categories category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.createDate = new Date();
        this.isDelete = false;
    }

    public product(Long id, String name, String image, Double price, Integer quantity, String createDate, Boolean isDelete, Categories category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;  
        this.quantity = quantity;
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.createDate = formatter.parse(createDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.isDelete = isDelete;
        this.category = category;
    }
}
