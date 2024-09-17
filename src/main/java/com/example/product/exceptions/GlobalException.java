package com.example.product.exceptions;

import com.example.product.handler.Response;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> sqlException(SQLIntegrityConstraintViolationException ex){
        Response<String> err = new Response<>(HttpStatus.BAD_REQUEST.value(), "the username has already taken", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }



}
