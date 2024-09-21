package com.example.product.services;

import com.example.product.dtos.UserDTO;
import com.example.product.entities.User;
import com.example.product.exceptions.DataNotFoundExceptionHandler;
import com.example.product.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        String pwHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(pwHash);
        return userRepository.save(user);
    }

    public User findOne(Long id){
        User user = new User();
        Optional<User> getUser = userRepository.findById(id);
        if(getUser.isEmpty()){
            throw new DataNotFoundExceptionHandler("data tidak ditemukan");
        } else{
            user = getUser.get();
        }
        return user;
    }

}
