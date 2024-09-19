package com.example.product.controllers;

import com.example.product.dtos.ProductDTO;
import com.example.product.entities.Product;
import com.example.product.handler.Response;
import com.example.product.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDTO product){
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
            @PageableDefault(size = 10) Pageable pageable
    ){
        Page<Product> products = productService.searchProducts(name, stock, pageable, sort, sortBy, createdByUsername);
        Response<Object> resp = new Response<>("berhasil ambil data", products, null);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

//    @GetMapping
//    public  ResponseEntity<Object> getAll(){
//        List<Product> products = productService.getAll();
//        Response<Object> response = new Response<>(HttpStatus.OK.value(), "sucess get data", products);
//
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}
