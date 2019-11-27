package com.blog.service.impl;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public boolean userNameIsExit(String username,String password) {
//        User user = userDao.findUsernameByUsername(username);
//        return user != null;
        User user = userDao.findUsernameByUsername(username);
        if(user!=null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }



    @Override
    public User findUserByPhone(String phone) {
        User user = userDao.findUserByphone(phone);

        return user;
    }

    @Override
    public String saveUser(User user) {
        user.setAvatarImgUrl("https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/user/avatar/noLogin_male.jpg");
        userDao.insertUser(user);
        return "2";
    }


}
