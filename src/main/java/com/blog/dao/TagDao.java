package com.blog.dao;

import com.blog.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {

    @Select("select * from tags order by id desc")
    public List<Tag> findTagCloud();

    @Select("select count(*) from tags ")
    public int countTagNum();

    @Insert("insert into tags (tagName,tagSize) values (#{tagName},#{tagSize})")
    public void insertTag(Tag tag);

    @Select("select IFNULL(max(id),0) from tags where TagName=#{TagName}")
    public int findIsExitByTagName(@Param("tagName") String tagName);
}
