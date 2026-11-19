package com.spendlytracker.spendlytracker.exception;
import jakarta.persistence.Enumerated;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import com.spendlytracker.spendlytracker.exception.ResourceNotFoundException;
import org.springframework.http.converter.HttpMessageNotReadableException;

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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(
            ResourceNotFoundException exception){

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", exception.getMessage()));
    }

/// error message for types
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRequest(
            HttpMessageNotReadableException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error",
                        "Invalid Transaction data. Check transaction type"
                ));
    }

/// error message for invalid transaction categories with type
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException exception
    ){

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", exception.getMessage()));
    }



    }
