package com.webservice.authors.exception;

public class NoRecordsException extends RuntimeException{
    public NoRecordsException(String message) {
        super(message);
    }
}
