package com.example.product.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserResponse {

    private Long id;
    private String username;
    private String email;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date createdAt;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date updatedAt;

}
