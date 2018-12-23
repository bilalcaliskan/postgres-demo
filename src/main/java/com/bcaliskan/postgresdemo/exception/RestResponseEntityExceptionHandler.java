package com.bcaliskan.postgresdemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleConflict(RuntimeException ex,
                                                 WebRequest request) {
        final String responseBody = "This should be application specific";
        return handleExceptionInternal(ex, responseBody,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class, ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex,
                                                                  WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { AccessDeniedException.class, AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex,
                                                              WebRequest request) {
        final String responseBody = "Access denied!";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}