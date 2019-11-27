package com.blog.dao;

import com.blog.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposiory extends ElasticsearchRepository<UserEntity,String> {



}
