package com.z7.collabowriteapi.security;

import com.z7.collabowriteapi.entity.User;
import com.z7.collabowriteapi.exception.AuthenticationProviderException;
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
import java.util.Arrays;

@Component
public class TokenValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private GitHubTokenValidator gitHubTokenValidator;

    final private String[] SUPPORTED_AUTHENTICATION_PROVIDERS = {"github"};

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access-token");
        String authProvider = request.getHeader("authentication-provider");
        UserCredentials userCredentials = null;
        //we need to check if the header exists or not
        if (accessToken == null) {
            throw new MissingAccessTokenException("missing access token");
        }
        if (authProvider == null || !Arrays.asList(SUPPORTED_AUTHENTICATION_PROVIDERS).contains(authProvider)) {
            throw new AuthenticationProviderException("authentication provider not specified");
        }
        if (!Arrays.asList(SUPPORTED_AUTHENTICATION_PROVIDERS).contains(authProvider)) {
            throw new AuthenticationProviderException("Authentication provider not supported, must be one of the following : " + SUPPORTED_AUTHENTICATION_PROVIDERS);
        }
        //check the token from the cache (not yet implemented)

        if (authProvider == "github") {
            //check the token from the authentication server if doesn't exist on the cache
            userCredentials = gitHubTokenValidator.validateToken(accessToken);
        }
        if (userCredentials == null) throw new InvalidAccessTokenException("invalid access token");
        TokenAuthentication tokenAuthentication = new TokenAuthentication(accessToken, userCredentials.getEmail(), true);
        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        filterChain.doFilter(request, response);
    }
}
