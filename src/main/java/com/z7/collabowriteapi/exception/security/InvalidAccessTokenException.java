package com.z7.collabowriteapi.exception.security;

import javax.naming.AuthenticationException;

public class InvalidAccessTokenException extends AuthenticationException {

    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
