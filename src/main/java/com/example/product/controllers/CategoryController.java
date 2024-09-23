package com.example.product.controllers;

import com.example.product.entities.Category;
import com.example.product.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getCategory(
            @RequestParam(required = false) Long id,
            @PageableDefault(size = 5) Pageable pageable
            ){
        Category result = categoryService.findOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    
}
