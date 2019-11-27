package com.blog.entity;

import lombok.Data;
import lombok.Getter;

import java.lang.annotation.Documented;
import java.util.Objects;

/**
 * 用户类
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String phone;

    private String password;

    private String gender;

    /**
     * 个人简介
     */
    private String personalBrief;

    private String email;

    //头像地址
    private String avatarImgUrl;

}
