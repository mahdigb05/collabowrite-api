package com.z7.collabowriteapi.exception;

import javax.naming.AuthenticationException;

public class MissingAccessTokenException extends AuthenticationException {
    public MissingAccessTokenException(String msg) {
        super(msg);
    }
}
