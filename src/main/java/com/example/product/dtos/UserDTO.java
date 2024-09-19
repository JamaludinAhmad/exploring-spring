package com.example.product.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserDTO {

    @NotBlank(message = "username tidak boleh kosong")
    private String username;

    @Email(message = "email harus valid")
    private String email;

    @Size(min = 10, max = 100, message = "must hava 10 char")
    private String password;
}
