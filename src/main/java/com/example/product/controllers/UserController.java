package com.example.product.controllers;

import com.example.product.entities.User;
import com.example.product.handler.Response;
import com.example.product.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>("tes", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User newUser = userService.create(user);
        Response<Object> resp = new Response<>(HttpStatus.CREATED.value(), "data inserted successfully", newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
