package com.blog.controller;

import com.blog.dao.ArticleDao;
import com.blog.entity.Article;
import com.blog.service.TagService;
import com.blog.utils.BuildArticleTabloidUtil;
import com.blog.utils.FileUtil;
import com.blog.utils.StringAndArray;
import com.blog.utils.TimeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class EditorController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    TagService tagService;

    @PostMapping("/publishArticle")
    @ResponseBody
    public JSONObject publishArticle(HttpServletRequest request, Article article,
                                     @RequestParam("articleGrade") String articleGrade) {
//        System.out.println(article.toString());
        JSONObject returnJson = new JSONObject();
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        String articleHtmlContent = buildArticleTabloidUtil.buildArticleTabloid(request.getParameter("articleHtmlContent"));
        article.setArticleContent(articleHtmlContent + "");
        String[] articleTags = request.getParameterValues("articleTagValue");
        String[] tags = new String[articleTags.length + 1];
        for (int i = 0; i < articleTags.length; i++) {
            //去掉可能出现的换行符
            articleTags[i] = articleTags[i].replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("\\s+", "").trim();
            tags[i] = articleTags[i];
        }
        tags[articleTags.length] = article.getArticleType();
        tagService.addTags(tags, Integer.parseInt(articleGrade));
        TimeUtil timeUtil = new TimeUtil();
        String id = request.getParameter("id");
        if ("".equals(id) && id != null) {
            String updateDate = timeUtil.getFormatDateForThree();
            article.setArticleTags(StringAndArray.arrayToString(tags));
            article.setUpdateDate(updateDate);
            article.setId(Integer.parseInt(id));
            article.setAuthor("作者：测试");
            return returnJson;
        }
        returnJson.put("status", "200");
        return returnJson;
    }

    @RequestMapping("uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        FileUtil fileUtil = new FileUtil();
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            String filepath = "E://temp/";
            System.out.println("================================");
            System.out.println("filepath" + filepath);
            System.out.println("================================");
            String fileContentType = file.getContentType();
            System.out.println("================================");
            System.out.println("fileContentType=" + fileContentType);
            System.out.println("================================");
            String fileExtension = fileContentType.substring(fileContentType.indexOf("/") + 1);
            System.out.println("================================");
            System.out.println("fileExtension=" + fileExtension);
            System.out.println("================================");
            String fileName = UUID.randomUUID() + "." + fileExtension;
            System.out.println("================================");
            System.out.println("fileName=" + fileName);
            System.out.println("================================");
            String subCatalog = "blogArticles/" + UUID.randomUUID();
            File fileUrl = fileUtil.multipartFileToFile(file, filepath, fileName);
            map.put("success", 1);
            map.put("message", "上传成功");
            map.put("url", "user/" + fileName);

        } catch (UnsupportedEncodingException e) {
            response.getWriter().write("{\"success\":0}");
            e.printStackTrace();
        }
        return map;
    }
}
