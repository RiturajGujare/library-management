package com.rituraj.library_management.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Global Exception Handler to handle custom exceptions and return appropriate HTTP responses
//RestControllerAdvice is a specialized version of @ControllerAdvice that combines @ControllerAdvice and @ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Helper method to build the response entity
    // This method creates a standardized error response structure
    private ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status) {
        Map<String, Object> error = new HashMap<>();  // Create a map to hold error details
        error.put("timestamp", LocalDate.now());  // Add the current date as the timestamp
        error.put("message", message);            // Add the exception message
        error.put("status", status.value());     // Add the HTTP status code

        // Return a ResponseEntity with the error map and the specified HTTP status
        return new ResponseEntity<>(error, status);
    }
    

    // Exception handler for BookNotFoundException
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBookNotFound(BookNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);  // Return a 404 Not Found status
    }

    // Exception handler for DuplicateIsbnException
    @ExceptionHandler(DuplicateIsbnException.class)
    public ResponseEntity<Map<String, Object>> handleduplicateIsbn(DuplicateIsbnException ex){
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);  // Return a 409 Conflict status
    }

    // Exception handler for DuplicateMemberIdException
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMemberNotFound(MemberNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);  // Return a 404 Not Found status
    }

    // Exception handler for IssueRecordNotFoundException
    @ExceptionHandler(IssueRecordNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIssueRecordNotFound(IssueRecordNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);   // Return a 404 Not Found status
    }
}
