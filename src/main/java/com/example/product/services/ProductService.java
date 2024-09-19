package com.example.product.services;

import com.example.product.dtos.ProductDTO;
import com.example.product.entities.Product;
import com.example.product.repositories.ProductRepository;
import com.example.product.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(ProductDTO productDTO){

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());

        return productRepository.save(product);
    }

    public Page<Product> searchProducts(String name, int stock, Pageable pageable, String description, String sortBy, String username){
        Sort sort = Sort.unsorted();

        if(!Objects.equals(description, "")){
            sort = description.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        }
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Specification<Product> spec = Specification.where(ProductSpecification.hasName(name))
                .and(ProductSpecification.hasGreaterThan(stock))
                .and(ProductSpecification.hasCreateByUsername(username));

        return productRepository.findAll(spec, sortedPage);
    }

}
