package com.z7.collabowriteapi.exception;

public class InvalidAccessTokenException extends Exception{

    public InvalidAccessTokenException() {
        super();
    }

    public InvalidAccessTokenException(String message) {
        super(message);
    }

    public InvalidAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccessTokenException(Throwable cause) {
        super(cause);
    }

    protected InvalidAccessTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
