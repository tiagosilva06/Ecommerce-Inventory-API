package com.project.ecommerce.inventory.exception;

import com.project.ecommerce.inventory.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handling Error 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException exception){

        ErrorResponse error = new ErrorResponse(404, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(404).body(error);
    }

    //Handling Error 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException exception){

        var errors = exception.getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList();

        ErrorResponse error = new ErrorResponse(400, errors.toString(), LocalDateTime.now());

        return ResponseEntity.badRequest().body(error);
    }

    //Handling Invalid Quantity
    @ExceptionHandler (InvalidQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidQuantity (InvalidQuantityException exception){

        ErrorResponse error = new ErrorResponse(400, exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.badRequest().body(error);
    }

    public record errorDataValidation(String field, String message){

        public errorDataValidation(FieldError error){
            this(
                    error.getField(), error.getDefaultMessage()
            );
        }

    }
}
