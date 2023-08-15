package com.example.recyclingapp.exceptions;

public class CartItemDoesNotExistException extends RuntimeException {
    public CartItemDoesNotExistException(String message) {
        super(message);
    }
}
