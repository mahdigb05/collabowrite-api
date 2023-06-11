package com.z7.collabowriteapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.z7.collabowriteapi.entity.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomBasicEntryPoint implements AuthenticationEntryPoint {


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=\"Realm\"");
        response.setContentType("application/json");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(authException.getMessage());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(errorResponse);


        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.flush();
        writer.close();

    }
}
