package com.example.product.dtos;

import com.example.product.entities.Category;
import com.example.product.entities.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

@Data
@Getter @Setter
public class ProductDTO {

    @NotBlank
    private String name;

    @Size(min = 10)
    private String description;

    @Min(value = 1)
    private Integer stock;

    @NotNull(message = "category id tidak boleh kosong")
    private Long category;

    @NotNull(message = "user id kosong")
    private Long createdBy;


}
