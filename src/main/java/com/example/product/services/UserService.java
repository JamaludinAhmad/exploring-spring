package com.example.product.services;

import com.example.product.dtos.UserDTO;
import com.example.product.entities.User;
import com.example.product.exceptions.DataNotFoundExceptionHandler;
import com.example.product.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(UserDTO user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return userRepository.save(newUser);
    }

    public Optional<User> findOne(Long id){

        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new DataNotFoundExceptionHandler("data tidak ditemukan");
        }
        return user;
    }

}
