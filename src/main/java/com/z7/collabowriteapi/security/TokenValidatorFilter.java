package com.z7.collabowriteapi.security;

import com.z7.collabowriteapi.entity.User;
import com.z7.collabowriteapi.exception.InvalidAccessTokenException;
import com.z7.collabowriteapi.exception.MissingAccessTokenException;
import com.z7.collabowriteapi.model.UserCredentials;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private GitHubTokenValidator gitHubTokenValidator;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("accessToken");
        //we need to check if the header exists or not
        if (accessToken == null) {
            throw new MissingAccessTokenException("missing access token");
        }
        //check the token from the cache (not yet implemented)
        //check the token from the authentication server if doesn't exist on the cache
        UserCredentials userCredentials = gitHubTokenValidator.validateToken(accessToken);
        if (userCredentials == null) throw new InvalidAccessTokenException("invalid access token");
        TokenAuthentication tokenAuthentication = new TokenAuthentication(accessToken, userCredentials.getEmail(), true);
        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        filterChain.doFilter(request, response);
    }
}
