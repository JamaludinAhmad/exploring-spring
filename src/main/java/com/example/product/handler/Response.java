package com.example.product.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private int statusCode;
    private String message;
    private T data;

}
