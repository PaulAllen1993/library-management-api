package com.library.API.Exception;

public class BookAlreadyReturnedException extends Exception {
    public BookAlreadyReturnedException(String message) {
        super(message);
    }
}
