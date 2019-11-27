package com.blog.service.impl;

import com.blog.dao.TagDao;
import com.blog.entity.Tag;
import com.blog.service.TagService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Override
    public JSONObject findTagCloud() {
        List<Tag> tagCloud = tagDao.findTagCloud();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status","200");
        jsonObject.put("result",tagCloud);
        jsonObject.put("tagNum",tagCloud.size());
        return jsonObject;
    }

    @Override
    public int countTagNum() {
        return tagDao.countTagNum();
    }

    @Override
    public void addTags(String[] tags, int tagSize) {
        for (String tag: tags
             ) {
            if(tagDao.findIsExitByTagName(tag) == 0){
                Tag t = new Tag();
                t.setTagName(tag);
                t.setTagSize(tagSize);
                tagDao.insertTag(t);
            }
        }
    }
}
