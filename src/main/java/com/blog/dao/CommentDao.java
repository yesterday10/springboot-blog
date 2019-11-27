package com.blog.dao;

import com.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    @Select("select ArticleId ,pid,answererId,respondentId,commentDate,likes,CommentContent from comment_record order by id desc")
    List<Comment> findFiveNewComment();

    @Select("select count(*) from comment_record")
    int countComment();

}