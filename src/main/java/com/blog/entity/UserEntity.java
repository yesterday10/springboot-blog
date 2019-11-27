package com.blog.entity;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "mymayikt", type = "user")
@Data
public class UserEntity {
    @Id
    private String id;
    private String name;
    private int sex;
    private int age;
}
