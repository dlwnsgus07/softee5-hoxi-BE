package com.allroundbeauty.server.exception;

public class AlreadyAcceptedException extends RuntimeException{
    public AlreadyAcceptedException(String message) {
        super(message);
    }

    public AlreadyAcceptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
