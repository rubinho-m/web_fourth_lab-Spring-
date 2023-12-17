package com.rubinho.fourth_lab.exceptions;

public class TokenUnauthorizedException extends RuntimeException{
    public TokenUnauthorizedException(String message) {
        super(message);
    }
}
