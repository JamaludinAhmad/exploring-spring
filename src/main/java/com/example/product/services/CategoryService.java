package com.example.product.services;

import com.example.product.entities.Category;
import com.example.product.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(String category, Pageable pageble){

        return categoryRepository.findByName(category);
    }
}
