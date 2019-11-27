package com.blog.service;


import com.blog.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    boolean userNameIsExit(String username,String password);

    User findUserByPhone(String phone);

    String saveUser(User user);

}
