package com.resendegabriel.investmentscontrolapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentExceptionHandler(IllegalArgumentException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Illegal Argument", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Invalid Body", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> entityNotFoundExceptionHandler(NoSuchElementException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Resource Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validationExceptionHandler(ValidationException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Validation error", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<StandardError> genericExceptionHandler(Exception ex, HttpServletRequest request, String error, HttpStatus status) {
        var standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI());
        log.error("STATUS {} - path:{} - message:{}", status.value(), request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(status).body(standardError.build());
    }
}
