package com.example.recyclingapp.exceptions;

public class CartItemAlreadyExistException extends RuntimeException{
    public CartItemAlreadyExistException(String message) {
        super(message);
    }
}
