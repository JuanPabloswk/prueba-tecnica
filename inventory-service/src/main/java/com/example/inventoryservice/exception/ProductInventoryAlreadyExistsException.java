package com.example.inventoryservice.exception;

public class ProductInventoryAlreadyExistsException extends RuntimeException {
    public ProductInventoryAlreadyExistsException(String message) {
        super(message);
    }
}
