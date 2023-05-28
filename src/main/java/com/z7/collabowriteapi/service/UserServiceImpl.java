package com.z7.collabowriteapi.service;

import com.z7.collabowriteapi.entity.User;
import com.z7.collabowriteapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
            private UserRepository userRepository;


    @Override
    public User findUserById(String userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) return user.get();
        throw new Exception("user not found");
    }
}
