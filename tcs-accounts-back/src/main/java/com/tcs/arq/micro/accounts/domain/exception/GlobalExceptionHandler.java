package com.tcs.arq.micro.accounts.domain.exception;

import com.tcs.arq.micro.accounts.utils.ErrorUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ResponseStatus statusAnnotation = ex.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status = (statusAnnotation != null) ? statusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> body = new HashMap<>();
        body.put(ErrorUtil.ResponseField.TIMESTAMP, Instant.now().toString());
        body.put(ErrorUtil.ResponseField.STATUS, status.value());
        body.put(ErrorUtil.ResponseField.ERROR, status.getReasonPhrase());
        body.put(ErrorUtil.ResponseField.MESSAGE, ex.getMessage());
        body.put(ErrorUtil.ResponseField.PATH, request.getRequestURI());

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(ErrorUtil.ResponseField.TIMESTAMP, Instant.now().toString());
        body.put(ErrorUtil.ResponseField.STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(ErrorUtil.ResponseField.ERROR, HttpStatus.BAD_REQUEST.getReasonPhrase());

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        body.put(ErrorUtil.ResponseField.MESSAGE, "Validation failed");
        body.put("errors", fieldErrors);
        body.put(ErrorUtil.ResponseField.PATH, request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(ErrorUtil.ResponseField.TIMESTAMP, Instant.now().toString());
        body.put(ErrorUtil.ResponseField.STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(ErrorUtil.ResponseField.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put(ErrorUtil.ResponseField.MESSAGE, ex.getMessage());
        body.put(ErrorUtil.ResponseField.PATH, request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
