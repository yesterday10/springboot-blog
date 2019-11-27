package com.blog.entity;

import lombok.Data;

/**
 * 留言
 */
@Data
public class Comment {

    /*留言id*/
    private Long id;
    /*文章id*/
    private Long ArticleId;
    /*回复的父id*/
    private Long pid;
    /*评论*/
    private int answererId;
    /*被回复者id*/
    private int respondentId;
    /*时间*/
    private String commentDate;
    /*喜欢*/
    private int likes;
    /*评论内容*/
    private String CommentContent;
}
