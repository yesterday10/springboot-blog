package com.blog.dao;

import com.blog.entity.LeaveMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LeaveMessageDao {

    @Select("select pageName,pId,answererId,respondentId,leaveMessageDate,leaveMessageContent from leave_message_record order by id desc")
    public List<LeaveMessage> findfiveNewLeaveWord();

    @Select("select count(*) from leave_message_record")
    int countLeaveMessageNum();

}
