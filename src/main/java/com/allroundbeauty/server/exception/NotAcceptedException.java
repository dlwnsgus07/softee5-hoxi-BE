package com.allroundbeauty.server.exception;

public class NotAcceptedException extends RuntimeException{
    public NotAcceptedException(String message) {
        super(message);
    }

    public NotAcceptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
