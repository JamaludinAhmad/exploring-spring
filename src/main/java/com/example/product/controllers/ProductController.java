package com.example.product.controllers;

import com.example.product.dtos.ProductDTO;
import com.example.product.dtos.ProductResponse;
import com.example.product.dtos.UserDTO;
import com.example.product.dtos.UserResponse;
import com.example.product.entities.Product;
import com.example.product.handler.Response;
import com.example.product.services.CategoryService;
import com.example.product.services.ProductService;
import com.example.product.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDTO dto){

        Product product = modelMapper.map(dto, Product.class);
        product.setCategory(categoryService.findOne(dto.getCategory()));
        product.setCreatedBy(userService.findOne(dto.getCreatedBy()));
        Product new_product = productService.create(product);

        Response<Object> response = new Response<>("product has inserted succesfully", new_product, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int stock,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "") String sort,
            @RequestParam(required = false) String createdByUsername,
            @RequestParam(required = false) Long category,
            @RequestParam(required = true, defaultValue = "5") Integer size,
            @RequestParam(required = true, defaultValue = "1") Integer page
    ){
        Page<Product> products = productService.searchProducts(name, stock, sort, sortBy, createdByUsername, category, size, page);
        Page<ProductResponse> result = products.map(product -> {
           UserResponse userResponse = modelMapper.map(product.getCreatedBy(), UserResponse.class);
           ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
           productResponse.setCreatedBy(userResponse);
           return productResponse;
        });
        Response<Object> resp = new Response<>("berhasil ambil data", result, null);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }


}
