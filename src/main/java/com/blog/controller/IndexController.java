package com.blog.controller;


import com.blog.entity.User;
import com.blog.service.*;
import com.blog.service.impl.IndexServiceImpl;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;

    @Autowired
    LeaveMessageService leaveMessageService;


    @PostMapping("/myArticles")
    @ResponseBody
    public JSONArray index(@RequestParam("pageNum")String pageNum,@RequestParam("rows")String rows){
        int i;
//        System.out.println(pageNum+rows+"i++");
        JSONArray jsonArray = indexService.findAllArticles(pageNum, rows);
//        System.out.println(jsonArray);
        return jsonArray;
    }


    @GetMapping("/register")
    public String register(){

        return "register";
    }
    @GetMapping("/header")
    public String header(){

        return "header";
    }

//    @GetMapping("/logins")
//    public String acc(){
//        return "login";
//    }

    @GetMapping("/login")
    public String acc1(){
        return "login";
    }


    @PostMapping("login")
    public String login1(@RequestParam("username") String username, @RequestParam("password") String password
    , HttpServletRequest request){
        boolean b = userService.userNameIsExit(username, password);
        if(b){
            return "index";
        }
        request.setAttribute("message","用户名不存在");
        return "login";
    }

    @PostMapping("register")
    @ResponseBody
    public String register(User user){
        String s = userService.saveUser(user);
        return s;
    }

    @GetMapping("/newComment")
    @ResponseBody
    public JSONObject newComment(@RequestParam("rows") String rows , @RequestParam("pageNum") String pageNum){
        return commentService.findFiveNewComment(Integer.parseInt(rows),Integer.parseInt(pageNum));

    }

    @GetMapping("findTagsCloud")
    @ResponseBody
    public JSONObject findTagsCloud(){
        return tagService.findTagCloud();
    }

    @GetMapping("newLeaveWord")
    @ResponseBody
    public JSONObject newLeaveWord(@RequestParam("rows") String rows , @RequestParam("pageNum") String pageNum){


        return leaveMessageService.findFiveNewComment(Integer.parseInt(rows),Integer.parseInt(pageNum));
    }

    @GetMapping("getSiteInfo")
    @ResponseBody
    public JSONObject getSiteInfo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tagsNum",tagService.countTagNum());
        jsonObject.put("leaveWordNum",leaveMessageService.countLeaveMessageNum());
        jsonObject.put("articleNum",indexService.countArticle());
        jsonObject.put("commentNum",commentService.countComment());
        return jsonObject;
    }



}
