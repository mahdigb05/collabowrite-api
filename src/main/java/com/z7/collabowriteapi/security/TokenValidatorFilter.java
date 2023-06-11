package com.z7.collabowriteapi.security;

import com.z7.collabowriteapi.exception.cache.TokenNotFoundInCache;
import com.z7.collabowriteapi.exception.cache.UserCredentialsNotFoundInCache;
import com.z7.collabowriteapi.exception.security.AuthenticationProviderException;
import com.z7.collabowriteapi.exception.security.InvalidAccessTokenException;
import com.z7.collabowriteapi.exception.security.MissingAccessTokenException;
import com.z7.collabowriteapi.model.UserCredentials;
import com.z7.collabowriteapi.service.TokenCacheService;
import com.z7.collabowriteapi.service.UserCredentialsCacheService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class TokenValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private GitHubTokenValidator gitHubTokenValidator;

    @Autowired
    private UserCredentialsCacheService userCredentialsCacheService;

    @Autowired
    private TokenCacheService tokenCacheService;

    final private String[] SUPPORTED_AUTHENTICATION_PROVIDERS = {"github"};

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access-token");
        String authProvider = request.getHeader("authentication-provider");
        UserCredentials userCredentials = null;
        //we need to check if the header exists or not
        if (accessToken == null) {
            throw new InsufficientAuthenticationException("missing accessToken");
        }
        if (authProvider == null) {
            throw new InsufficientAuthenticationException("authentication provider not specified");
        }
        if (!Arrays.asList(SUPPORTED_AUTHENTICATION_PROVIDERS).contains(authProvider)) {
            throw new BadCredentialsException("Authentication provider not supported, must be one of the following : " + SUPPORTED_AUTHENTICATION_PROVIDERS);
        }
        //check the token from the cache (not yet implemented)
        //check if user with the same token exists in cache
        try {
            userCredentials = userCredentialsCacheService.getUserCredentials(accessToken);
        } catch (UserCredentialsNotFoundInCache ex) {
            // user was not found in cache we need to check it from the provider's auth server
            if (authProvider == "github") {
                //check the token from the authentication server if doesn't exist on the cache
                userCredentials = gitHubTokenValidator.validateToken(accessToken);
                if (userCredentials == null) throw new BadCredentialsException("invalid access token");
                //setting credentials & token
                userCredentialsCacheService.setUserCredentials(accessToken, userCredentials);
                tokenCacheService.setToken(userCredentials.getEmail(), accessToken);

                //clear both user and token cache
                try {
                    String deleteToken = tokenCacheService.removeToken(userCredentials.getEmail());
                    userCredentialsCacheService.removeUserCredentials(deleteToken);
                } catch (TokenNotFoundInCache tokenNotFoundInCache) {
                    //do nothing
                }
            }
        }
        TokenAuthentication tokenAuthentication = new TokenAuthentication(accessToken, userCredentials.getEmail());
        SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        filterChain.doFilter(request, response);
    }
}
