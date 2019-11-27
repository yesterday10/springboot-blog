package com.blog.service.impl;

import com.blog.dao.LeaveMessageDao;
import com.blog.dao.UserDao;
import com.blog.entity.LeaveMessage;
import com.blog.service.LeaveMessageService;
import com.blog.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject findFiveNewComment(int rows, int pageNum) {
        JSONObject returnJson = new JSONObject();
        PageHelper.startPage(pageNum,rows);
        List<LeaveMessage> leaveMessages = leaveMessageDao.findfiveNewLeaveWord();
        PageInfo<LeaveMessage> pageInfo = new PageInfo<>(leaveMessages);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for (LeaveMessage leaveMessage: leaveMessages
             ) {
            jsonObject = new JSONObject();
            jsonObject.put("pagePath",leaveMessage.getPageName());
            jsonObject.put("answerer",userDao.findUserNameById(leaveMessage.getAnswererId()).getUsername());
            jsonObject.put("leaveWordDate",leaveMessage.getLeaveMessageDate().substring(0,10));
            jsonObject.put("leaveWordContent",leaveMessage.getLeaveMessageContent());
            jsonArray.add(jsonObject);
        }
        returnJson.put("Status","200");
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
    public int countLeaveMessageNum() {

        return leaveMessageDao.countLeaveMessageNum();
    }
}
