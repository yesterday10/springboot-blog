package com.blog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.Objects;

@Data
public class TreeDTO {

    private Long id;
    private long parentId;
    private String name;
    private String menuUrl;
    private boolean checked;
    private String icon;
    private List<TreeDTO> children;

    public TreeDTO(Long id, long parentId, String name, String menuUrl, boolean checked, String icon) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.menuUrl = menuUrl;
        this.checked = checked;
        this.icon = icon;
    }
}
