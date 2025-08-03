package com.example.inventoryservice.handler;

import com.example.inventoryservice.exception.InsufficientStockException;
import com.example.inventoryservice.exception.ProductNotFoundException;
import com.example.inventoryservice.exception.ProductNotInInventoryException;
import com.example.inventoryservice.utils.JsonApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<JsonApiError> handleProductNotFound(ProductNotFoundException ex) {
        JsonApiError error = new JsonApiError(HttpStatus.NOT_FOUND.value(), "PRODUCT_NOT_FOUND", "Product not found",
                ex.getMessage(), "productId", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotInInventoryException.class)
    public ResponseEntity<JsonApiError> handleInventoryMissing(ProductNotInInventoryException ex) {
        JsonApiError error = new JsonApiError(
                HttpStatus.NOT_FOUND.value(), "PRODUCT_NOT_IN_INVENTORY", "Product not found in inventory",
                ex.getMessage(), "inventory", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<JsonApiError> handleInsufficientStock(InsufficientStockException ex) {
        JsonApiError error = new JsonApiError(
                HttpStatus.BAD_REQUEST.value(), "INSUFFICIENT_STOCK", "Insufficient stock",
                ex.getMessage(), "quantity",  LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonApiError> handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));

        JsonApiError error = new JsonApiError(
                HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR", "Validation failed", detail, "input",  LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonApiError> handleGenericException(Exception ex) {
        JsonApiError error = new JsonApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_ERROR", "Unexpected error",
                ex.getMessage(), "server", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
