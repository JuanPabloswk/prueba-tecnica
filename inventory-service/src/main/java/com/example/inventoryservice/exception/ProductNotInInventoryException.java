package com.example.inventoryservice.exception;

public class ProductNotInInventoryException extends RuntimeException  {
    public ProductNotInInventoryException(String message) {
        super(message);
    }
}
