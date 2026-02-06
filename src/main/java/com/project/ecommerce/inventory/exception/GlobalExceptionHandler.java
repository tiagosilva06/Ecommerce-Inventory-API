package com.project.ecommerce.inventory.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratamento do erro 404
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(){
        return ResponseEntity.notFound().build();
    }

    //Tratamento erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<errorDataValidation>> handleBadRequest(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(errorDataValidation::new).toList());
    }

    public record errorDataValidation(String field, String message){

        public errorDataValidation(FieldError error){
            this(
                    error.getField(), error.getDefaultMessage()
            );
        }

    }
}
