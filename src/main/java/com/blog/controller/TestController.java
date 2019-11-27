package com.blog.controller;

import com.blog.dao.UserDao;
import com.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test1")
    public String message(Model model){

        User user = userDao.findUserNameById(1);
        model.addAttribute("message",user.getUsername());

        return "test1";
    }



}
