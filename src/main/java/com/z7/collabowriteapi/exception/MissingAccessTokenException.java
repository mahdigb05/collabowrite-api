package com.z7.collabowriteapi.exception;

public class MissingAccessTokenException extends Exception{
    public MissingAccessTokenException() {
        super();
    }

    public MissingAccessTokenException(String message) {
        super(message);
    }

    public MissingAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingAccessTokenException(Throwable cause) {
        super(cause);
    }

    protected MissingAccessTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
