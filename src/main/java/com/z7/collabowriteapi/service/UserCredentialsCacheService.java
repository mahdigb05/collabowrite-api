package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.exception.cache.UserCredentialsNotFoundInCache;
import com.z7.collabowriteapi.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserCredentialsCacheService {

    private String CACHE_KEY = "USER";
    @Autowired
    private RedisTemplate redisTemplate;

    public UserCredentials getUserCredentials(String token) throws UserCredentialsNotFoundInCache {
        ObjectHashMapper hashMapper = new ObjectHashMapper();
        Map<byte[], byte[]> hashEntries = redisTemplate.opsForHash().entries(token);
        if (hashEntries == null) throw new UserCredentialsNotFoundInCache("user credentials not found in cache");
        UserCredentials userCredentials = (UserCredentials) hashMapper.fromHash(hashEntries);
        return userCredentials;
    }

    public UserCredentials setUserCredentials(String token, UserCredentials userCredentials) {
        redisTemplate.opsForHash().put(CACHE_KEY, token, userCredentials);
        return userCredentials;
    }

    public void removeUserCredentials(String token) {
        redisTemplate.opsForHash().delete(CACHE_KEY, token);
    }

}
