package com.example.product.services;

import com.example.product.entities.Category;
import com.example.product.exceptions.DataNotFoundExceptionHandler;
import com.example.product.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(String category, Pageable pageble){

        return categoryRepository.findByName(category);
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) throw new DataNotFoundExceptionHandler("category tidak ditemukan");

        return category.get();
    }
}
