package com.rubinho.fourth_lab.controller;

import com.rubinho.fourth_lab.exceptions.InvalidDataException;
import com.rubinho.fourth_lab.exceptions.TokenUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenUnauthorizedException.class)
    public ResponseEntity<String> unauthorized (Exception ex){
        return new ResponseEntity<>("Auth error: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidData (Exception ex){
        return new ResponseEntity<>("Invalid Data: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> exception (Exception ex){
//        return new ResponseEntity<>("Server error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
