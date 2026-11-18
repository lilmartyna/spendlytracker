package com.spendlytracker.spendlytracker.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;

/// handles exceptions

@RestControllerAdvice
public class GlobalExceptionHandler {

/// handles validation errors
@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            ConstraintViolationException exception){
    String errorMessage = exception.getConstraintViolations()
            .iterator()
            .next()
            .getMessage();

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("error", errorMessage));
            }
}
