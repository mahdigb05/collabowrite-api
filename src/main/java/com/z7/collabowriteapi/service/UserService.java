package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.User;

public interface UserService {

    public User findUserById(String userId) throws Exception;

}
