package com.blog.service;

import net.sf.json.JSONObject;

public interface TagService {

    /**
     * 获得标签云
     * @return
     */
    JSONObject findTagCloud();

    public int countTagNum();

    public void addTags(String[]tags ,int tagSize);
}
