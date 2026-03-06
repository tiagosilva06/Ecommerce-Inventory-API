package com.project.ecommerce.inventory.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Bean Validation Errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException exception) {

        List<String> errors = exception.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                400,
                String.join(", ", errors),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }

    // 400 - Invalid Quantity (business rule)
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidQuantity(InvalidQuantityException exception) {

        ErrorResponse error = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(error);
    }

    // 404 - Product Not Found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(error);
    }

    //400 - Insufficient inventory
    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientInventory(InsufficientInventoryException exception){

        ErrorResponse error = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(400).body(error);
    }
}