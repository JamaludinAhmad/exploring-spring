package com.example.product.exceptions;

import com.example.product.handler.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalException {

    @ResponseStatus
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> sqlException(SQLIntegrityConstraintViolationException ex){
        Response<String> err = new Response<>("the username has already taken", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ResponseStatus
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationInputException(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(((FieldError)error).getField() + " : " + error.getDefaultMessage());
        });

        Response<Object> resp = new Response<>("salah", null, errors);

//        TODO: you must change this
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }



}
