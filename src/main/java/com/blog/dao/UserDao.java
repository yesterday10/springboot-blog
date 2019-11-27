package com.blog.dao;

import com.blog.entity.Role;
import com.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where username=#{username}")
    User findUsernameByUsername(@Param("username") String username);

    @Select("select * from user where phone=#{phone}")
    User findUserByphone(@Param("phone")String phone);

    @Select("select * from user where phone=#{phone}")
    @Results({
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "phone", property = "roles", javaType = List.class, many = @Many(select = "com.zhy.mapper.UserMapper.getRoleNameByPhone")),
    })
    User getUsernameAndRolesByPhone(@Param("phone") String phone);

    @Select("select r.name from user u LEFT JOIN user_role sru on u.id= sru.User_id LEFT JOIN role r on sru.Role_id=r.id where phone=#{phone}")
    Role getRoleNameByPhone(String phone);

    @Insert("insert into user (phone,username,password,gender,avatarImgUrl) values (#{phone},#{username},#{password},#{gender},#{avatarImgUrl})")
    void insertUser(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User checkUser(@Param("username") String username,@Param("password") String password);

    @Select("select username from user where id=#{id}")
    User findUserNameById(int id);

}
//endregion
