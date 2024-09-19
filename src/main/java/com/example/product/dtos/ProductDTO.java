package com.example.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

@Data
@Getter @Setter
public class ProductDTO {

    @NotBlank
    private String name;

    @Size(min = 10)
    private String description;

    @Size(min = 1)
    private int stock;

    @NotBlank
    private int categoryId;

    @NotBlank
    private CreatedBy createdBy;
}
