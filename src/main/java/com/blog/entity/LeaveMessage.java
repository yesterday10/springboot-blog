package com.blog.entity;

import lombok.Data;

@Data
public class LeaveMessage {
    private int id;

    private String pageName;

    private int pId =0;

    private int answererId;

    private int respondentId;

    private String leaveMessageDate;

    private int likes = 0;
    /**
     * 留言内容
     */
    private String leaveMessageContent;

}
