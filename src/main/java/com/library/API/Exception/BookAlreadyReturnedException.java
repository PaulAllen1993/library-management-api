package com.contal.API.Exception;

public class BookAlreadyReturnedException extends Exception {
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
