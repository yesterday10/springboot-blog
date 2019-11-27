package com.blog.controller;

import com.blog.dao.ArticleDao;
import com.blog.entity.Article;
import com.blog.service.IndexService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

@RestController
public class ShowController {

    @Autowired
    ArticleDao articleDao;
    
    @Autowired
    IndexService indexService;

    @PostMapping("/getArticleByArticleId")
    @ResponseBody
    public JSONObject getArticleById(@RequestParam("articleId") String articleId){
//        System.out.println("articleId"+articleId);
//        Article article = articleDao.getActicleByActicleID(Long.parseLong(articleId));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status","200");
//        jsonObject.put("author",article.getAuthor());
////        jsonObject.put("articleId","1532884460");
//        jsonObject.put("originalAuthor",article.getOriginalAuthor());
//        jsonObject.put("articleTitle",article.getArticleTitle());
//        jsonObject.put("publishDate",article.getPublishDate());
//        jsonObject.put("updateDate",article.getUpdateDate());
//        jsonObject.put("articleContent",article.getArticleContent());
//        jsonObject.put("articleTags", article.getArticleTags());
//        jsonObject.put("articleType",article.getArticleType());
//        jsonObject.put("articleCategories",article.getArticleCategories());
//        jsonObject.put("articleUrl",article.getArticleUrl());
//        jsonObject.put("likes",article.getLikes());
       JSONObject jsonObject = indexService.getArticleByArticleId(Long.parseLong(articleId));
        return jsonObject;
    }

}
