package com.z7.collabowriteapi.exception.cache;

public class TokenNotFoundInCache extends Exception{
    public TokenNotFoundInCache() {
        super();
    }

    public TokenNotFoundInCache(String message) {
        super(message);
    }

    public TokenNotFoundInCache(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenNotFoundInCache(Throwable cause) {
        super(cause);
    }

    protected TokenNotFoundInCache(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
