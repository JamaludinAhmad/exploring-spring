package com.example.product.controllers;

import com.example.product.entities.Product;
import com.example.product.handler.Response;
import com.example.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        Product new_product = productService.create(product);
        Response<Object> response = new Response<>(HttpStatus.CREATED.value(), "product has inserted succesfully", new_product);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int stock,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "") String sort,
            @RequestParam(required = false) String createdByUsername,
            @PageableDefault(size = 5) Pageable pageable
    ){

        Page<Product> products = productService.searchProducts(name, stock, pageable, sort, sortBy, createdByUsername);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

//    @GetMapping
//    public  ResponseEntity<Object> getAll(){
//        List<Product> products = productService.getAll();
//        Response<Object> response = new Response<>(HttpStatus.OK.value(), "sucess get data", products);
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}
