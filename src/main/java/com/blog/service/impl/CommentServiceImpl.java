package com.blog.service.impl;

import com.blog.dao.ArticleDao;
import com.blog.dao.CommentDao;
import com.blog.dao.UserDao;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import com.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;


    @Override
    public JSONObject findFiveNewComment(int rows, int pageNum) {
        JSONObject returnJson= new JSONObject();
        PageHelper.startPage(pageNum,rows);
        List<Comment> comment = commentDao.findFiveNewComment();
        PageInfo<Comment> pageInfo = new PageInfo<>(comment);
        JSONArray jsonArray =new JSONArray();
        for (Comment comments: comment
             ) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("articleId",comments.getArticleId());
            jsonObject1.put("answerer",userDao.findUserNameById(comments.getAnswererId()).getUsername());
            jsonObject1.put("commentDate",comments.getCommentDate());
            jsonObject1.put("commentContent",comments.getCommentContent());
            jsonObject1.put("articleTitle",articleDao.findArticleTitleByArticleId(comments.getArticleId()).getArticleTitle());
            jsonArray.add(jsonObject1);
        }
        returnJson.put("status","200");
        returnJson.put("result",jsonArray);
        JSONObject PageJson = new JSONObject();
        PageJson.put("pageNum", pageInfo.getPageNum());
        PageJson.put("pageSize", pageInfo.getPageSize());
        PageJson.put("total", pageInfo.getTotal());
        PageJson.put("pages", pageInfo.getPages());
        PageJson.put("isFirstPage", pageInfo.isIsFirstPage());
        PageJson.put("lastPage",pageInfo.isIsLastPage());
        returnJson.put("pageInfo",pageInfo);
        return returnJson;
    }

    @Override
    public int countComment() {
        return commentDao.countComment();
    }
}
