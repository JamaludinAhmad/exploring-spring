package com.example.product.dtos;

import com.example.product.entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Long stock;
    private UserResponse createdBy;
    private Category category;

}
