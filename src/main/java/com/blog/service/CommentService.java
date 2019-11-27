package com.blog.service;


import com.blog.entity.Comment;
import net.sf.json.JSONObject;

import java.util.List;

public interface CommentService {

    public JSONObject findFiveNewComment(int rows, int pageNum);

    int countComment();
}
