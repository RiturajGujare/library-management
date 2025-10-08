package com.rituraj.library_management.exception;

public class BookNotFoundByIsbnException extends RuntimeException{
    
    public BookNotFoundByIsbnException(String message){
        super(message);
    }
    
}
