package com.blog.service.impl;

import com.sun.xml.internal.fastinfoset.util.StringArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.blog.dao.ArticleDao;
import com.blog.entity.Article;
import com.blog.service.IndexService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    ArticleDao articleDao;

    /**
     * 字符串转换成字符串数组
     * @param str 字符串
     * @return 转换后的字符串数组
     */
    public  String[] stringToArray(String str){
        String[] array = str.split(",");
        for(int i=0;i<array.length;i++){
            array[i] = array[i].trim();
        }
        return array;
    }

    @Override
    public JSONArray findAllArticles(String rows, String pageNo) {
        int pageNum = Integer.parseInt(rows);
        int pageSize= Integer.parseInt(pageNo);
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleDao.findAllArtilces();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        List<Map<String,Object>>  newarticles = new ArrayList<>();
        Map<String ,Object> map;
        for (Article article: articles){
            map = new HashMap<>();
            map.put("thisArticleUrl",article.getArticleId());
            map.put("articleTags", this.stringToArray(article.getArticleTags()));
            map.put("articleTitle", article.getArticleTitle());
            map.put("articleType", article.getArticleType());
            map.put("publishDate", article.getPublishDate());
            map.put("originalAuthor", article.getOriginalAuthor());
            map.put("articleCategories", article.getArticleCategories());
            map.put("articleTabloid", article.getArticleTabloid());
            map.put("likes", article.getLikes());
            newarticles.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(newarticles);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("pageNum",pageInfo.getPageNum());
        map1.put("pageSize",pageInfo.getPageSize());
        map1.put("total",pageInfo.getTotal());
        map1.put("pages",pageInfo.getPages());
        map1.put("isFirstPage",pageInfo.isIsFirstPage());
        map1.put("isLastPage",pageInfo.isIsLastPage());
        jsonArray.add(map1);
        return jsonArray;
    }

    @Override
    public JSONObject getArticleByArticleId(Long articleId) {
        Article article = articleDao.getActicleByActicleID(articleId);
        System.out.println("-----------------------");
        System.out.println(article.getNextArticleId());
        System.out.println("-----------------------");
        Article nextArticle = articleDao.findArticleByArticleId(article.getNextArticleId());
        Article lastArticle = articleDao.findArticleByArticleId(article.getLastArticleId());
        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("thisArticleUrl",articleId);
//        jsonObject.put("articleTags", article.getArticleTags());
//        jsonObject.put("articleTitle", article.getArticleTitle());
//        jsonObject.put("articleType", article.getArticleType());
//        jsonObject.put("publishDate", article.getPublishDate());
//        jsonObject.put("originalAuthor", article.getOriginalAuthor());
//        jsonObject.put("articleCategories", article.getArticleCategories());
//        jsonObject.put("articleTabloid", article.getArticleTabloid());
//        jsonObject.put("likes", article.getLikes());
        jsonObject.put("status","200");
        jsonObject.put("author",article.getAuthor());
        jsonObject.put("articleId","1532884460");
        jsonObject.put("originalAuthor",article.getOriginalAuthor());
        jsonObject.put("articleTitle",article.getArticleTitle());
        jsonObject.put("publishDate",article.getPublishDate());
        jsonObject.put("updateDate",article.getUpdateDate());
        jsonObject.put("articleContent",article.getArticleContent());
        jsonObject.put("articleTags", this.stringToArray(article.getArticleTags()));
        jsonObject.put("articleType",article.getArticleType());
        jsonObject.put("articleCategories",article.getArticleCategories());
        jsonObject.put("articleUrl",article.getArticleUrl());
        jsonObject.put("likes",article.getLikes());
        jsonObject.put("status","200");
        jsonObject.put("nextArticleUrl",nextArticle.getArticleId());
        jsonObject.put("lastArticleUrl",lastArticle.getArticleId());
        jsonObject.put("lastArticleTitle",lastArticle.getArticleTitle());
        jsonObject.put("nextArticleTitle",nextArticle.getArticleTitle());
        jsonObject.put("lastStatus","200");
        jsonObject.put("nextStatus","200");
        return jsonObject;
    }

    @Override
    public int countArticle() {
        return articleDao.countArticle();
    }


}
