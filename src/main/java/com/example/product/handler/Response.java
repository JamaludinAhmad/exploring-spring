package com.example.product.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter @Setter
public class Response<T> {
    private String message;
    private T data;
    private Object errors;


}
