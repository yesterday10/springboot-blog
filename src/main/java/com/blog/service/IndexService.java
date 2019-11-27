package com.blog.service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IndexService {

    JSONArray findAllArticles(String rows, String pageNo);

    JSONObject getArticleByArticleId(Long article);

    int countArticle();

}
