package com.example.product.controllers;

import com.example.product.dtos.UserDTO;
import com.example.product.entities.User;
import com.example.product.handler.Response;
import com.example.product.services.UserService;
import jakarta.validation.Valid;
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

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOne(@PathVariable("userId") Long id){
        Response<Object> resp = new Response<>("data berhasil didapatkan", userService.findOne(id), null);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

//    @PutMapping("/{userId}")
//    public ResponseEntity<Object> update(@PathVariable("userId") Long id, @Valid UserDTO){
//
//    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO user){
        User newUser = userService.create(user);
        Response<Object> resp = new Response<>("data inserted successfully", newUser, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

}
