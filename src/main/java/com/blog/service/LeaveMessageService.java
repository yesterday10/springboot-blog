package com.blog.service;

import net.sf.json.JSONObject;

public interface LeaveMessageService {

    JSONObject findFiveNewComment(int rows,int pageNum);


    /**
     * 获得留言总数
     */
    int countLeaveMessageNum();
}
