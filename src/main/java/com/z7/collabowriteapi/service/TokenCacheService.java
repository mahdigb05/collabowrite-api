package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.exception.cache.TokenNotFoundInCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenCacheService {


    @Autowired
    private RedisTemplate redisTemplate;

    private String CACHE_KEY = "TOKEN";

    public String getToken(String email) throws TokenNotFoundInCache {
        String token = (String) redisTemplate.opsForHash().get(CACHE_KEY, email);
        if (token == null) throw new TokenNotFoundInCache("could not locate the token in cache ");
        return token;
    }

    public String setToken(String email, String token) {
        redisTemplate.opsForHash().put(CACHE_KEY, email, token);
        return token;
    }

    public String removeToken(String email) throws TokenNotFoundInCache {
        String token = this.getToken(email);
        redisTemplate.opsForHash().delete(CACHE_KEY, email);
        return token;
    }
}
