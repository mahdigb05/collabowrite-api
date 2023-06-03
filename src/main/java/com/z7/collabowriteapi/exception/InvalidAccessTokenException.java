package com.z7.collabowriteapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.AuthenticationException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidAccessTokenException extends AuthenticationException {

    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
