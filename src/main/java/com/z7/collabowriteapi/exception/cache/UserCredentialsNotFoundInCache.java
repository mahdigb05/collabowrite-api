package com.z7.collabowriteapi.exception.cache;

import lombok.Data;

public class UserCredentialsNotFoundInCache extends Exception {

    public UserCredentialsNotFoundInCache() {
        super();
    }

    public UserCredentialsNotFoundInCache(String message) {
        super(message);
    }

    public UserCredentialsNotFoundInCache(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCredentialsNotFoundInCache(Throwable cause) {
        super(cause);
    }

    protected UserCredentialsNotFoundInCache(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
