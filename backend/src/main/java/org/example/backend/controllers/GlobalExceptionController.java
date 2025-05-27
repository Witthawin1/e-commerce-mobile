package org.example.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.backend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(SaleItemNotFoundException.class)
    public ResponseEntity<MyErrorResponse> handleSaleItemNotFoundException(
            SaleItemNotFoundException ex , HttpServletRequest request
    ){
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.NOT_FOUND.value() , "Not Found", ex.getMessage() , request.getRequestURI()
        );
        return ResponseEntity.status(myErrorResponse.getStatus()).body(myErrorResponse);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<MyErrorResponse> handleBrandNotFoundException(
            BrandNotFoundException ex , HttpServletRequest request
    ){
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                HttpStatus.NOT_FOUND.value() , "Not Found", ex.getMessage() , request.getRequestURI()
        );
        return ResponseEntity.status(myErrorResponse.getStatus()).body(myErrorResponse);
    }
    @ExceptionHandler(DuplicateBrandException.class)
    public ResponseEntity<MyErrorResponse> handleDuplicateBrandException(
            DuplicateBrandException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidBrandException.class)
    public ResponseEntity<MyErrorResponse> handleInvalidBrandDataException(
            InvalidBrandException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(BrandhasSaleItemException.class)
    public ResponseEntity<MyErrorResponse> handleBrandhasSaleItemException(
            Exception ex, HttpServletRequest request) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<MyErrorResponse> buildErrorResponse(
            Exception ex, HttpStatus status, HttpServletRequest request) {
        MyErrorResponse myErrorResponse = new MyErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(myErrorResponse);
    }


}
