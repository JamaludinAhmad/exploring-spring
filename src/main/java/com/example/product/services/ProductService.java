package com.example.product.services;

import com.example.product.dtos.ProductDTO;
import com.example.product.entities.Product;
import com.example.product.exceptions.DataNotFoundExceptionHandler;
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

    public Product create(Product product){

        return productRepository.save(product);
    }

    public Page<Product> searchProducts(String name, int stock, Pageable pageable, String description, String sortBy, String username, String category){
        Sort sort = Sort.unsorted();

        if(!Objects.equals(description, "")){
            sort = description.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        }
        Pageable sortedPage = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Specification<Product> spec = Specification.where(ProductSpecification.hasName(name))
                .and(ProductSpecification.hasGreaterThan(stock))
                .and(ProductSpecification.hasCreateByUsername(username))
                .and(ProductSpecification.hasCategoryName(category));

        Page<Product> result = productRepository.findAll(spec, sortedPage);
        if(result.getTotalElements() == 0) throw new DataNotFoundExceptionHandler("Data tidak ditemukan");
        return productRepository.findAll(spec, sortedPage);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Product update(ProductDTO productDTO){
//         TODO: edit
        return null;
    }

}
