package com.z7.collabowriteapi.exception.security;

import javax.naming.AuthenticationException;

public class AuthenticationProviderException extends AuthenticationException {
    public AuthenticationProviderException(String msg) {
        super(msg);
    }
}
