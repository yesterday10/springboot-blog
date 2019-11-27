package com.blog.dao;

import com.blog.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDao {

    /**
     * 保存文章
     * @param article
     */
    @Insert("insert into article (id,articleId,author,originalAuthor,articleTitle,publishDate,updateDate,articleContent,articleTags,articleType," +
            "articleCategories,articleUrl,articleTabloid,lastArticleId,nextArticleId,likes)" +
            "values(#{id},#{articleId},#{author},#{originalAuthor},#{articleTitle},#{publishDate},#{updateDate},#{articleContent},#{articleTags}," +
            "#{articleType},#{articleCategories},#{articleUrl},#{articleTabloid},#{lastArticleId},#{nextArticleId},#{likes})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Article article);

    /**
     * 更新文章
     * @param article
     */
    @Update("update article set originalAuthor=#{originalAuthor},articleTitle=#{articleTitle}," +
            "updateDate=#{updateDate},articleContent=#{articleContent},articleTags=#{articleTags}," +
            "articleType=#{articleType}，articleCategories=#{articleCategories}，articleUrl=#{articleUrl}," +
            "articleTabloid=#{articleTabloid} where id=#{id}")
    void updateArticleById(Article article);

    /**
     * 根据id获取url
     * @param id
     * @return
     */
    @Select("select articleId from article where id=#{id}")
    Article getArticleUrlById(int id);

    /**
     *根据文章id获取文章
     */
    @Select("select * from article where articleId=#{articleId}")
    Article getActicleByActicleID(@Param("articleId") long articleId);

    /**
     * 根据文章id查找文章的标题
     * @param articleId
     * @return
     */
    @Select("select articleTitle ,articleTabloid from article where articleId=#{articleId} ")
    Article findArticleTitleByArticleId(@Param("articleId") long articleId);

    /**
     * 根据文章id查找文章
     * @param articleId
     * @return
     */
    @Select("select articleId,articleTitle from article where articleId = #{articleId}")
    Article findArticleByArticleId(@Param("articleId") long articleId);

    @Select("select articleId,originalAuthor,articleTags,articleTitle,articleType,publishDate,originalAuthor,articleCategories,articleTabloid,likes from article order by id desc ")
    List<Article> findAllArtilces();

    @Select("select articleId from article order by id desc limit 1")
    Article findEndArticleId();

    @Update("update article set lastArticleId=#{lastArticleId} where articleId=#{articleId}")
    void updateArticleLastId(@Param("lastArticleId") Long lastArticleId,@Param("articleId") Long articleId);

    @Update("update article set nextArticleId=#{nextArticleId} where articleId=#{articleId}")
    void updateArticleNextId(@Param("nextArticleId") Long nextArticleId,@Param("articleId") Long articleId);

    /**
     *
     * @param articleId
     */
    @Update("update article set likes=likes+1 where articleId=#{articleId}")
    void updateLikeByArticleId(@Param("articleId") String articleId);

    @Select("select count(*) from article")
    int countArticle();

}
