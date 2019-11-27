package com.blog.controller;

import com.blog.dao.ArticleDao;
import com.blog.dao.PermissionDao;
import com.blog.entity.Permission;
import com.blog.entity.TreeDTO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 页面跳转页面
 * @author 12sigma
 */
@RESTController
public class BackController {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    PermissionDao permissionDao;

    @GetMapping("/index")
    public String  index(){
        return "index";
    }

    @GetMapping("editor")
    public String editor(){
        return "editor";
    }

//    @GetMapping("/show")
//    public String show(){
//        return "show";
//    }

    @GetMapping("/file")
    public String file(){
        return "file";
    }

    @GetMapping("/{articleId}")
    public String show(@PathVariable("articleId") String articleId, HttpServletResponse response,
    Model model,HttpServletRequest request){
//        System.out.println(articleId);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");
        request.setAttribute("articleId","articleId");
        response.setHeader("articleId",String.valueOf(articleId));
//        Article acticle = articleDao.getActicleByActicleID(articleId);
      //  System.out.println("aaa"+ acticle.getArticleId());
     //   response.setHeader("articleId",String.valueOf(acticle.getArticleId()));
        return "show";
    }

    @GetMapping("/test")
    public String test(Model model){
        Set<TreeDTO> treeDTOS = new HashSet<>();
        TreeDTO tree1;
        List<Permission> list = permissionDao.findAll();
//
//        for (Permission permission : list) {
//            System.out.println(permission.getResourceType());
//        }

        for (Permission permission : list) {
            if ("menu".equals(permission.getResourceType())){
                tree1 = new TreeDTO(permission.getId(),permission.getParentId(),permission.getName(),permission.getUrl(),false,permission.getIcon());
                treeDTOS.add(tree1);
            }
        }
        for (TreeDTO treeDTO : treeDTOS) {
            //System.out.println(treeDTO);
        }
        Set<TreeDTO> rootTrees = new HashSet<>();
        for (TreeDTO rootTree : treeDTOS) {
            if (rootTree.getParentId() == 0){
                rootTrees.add(rootTree);
            }
            for (TreeDTO rootTree1 : treeDTOS){
                if (rootTree1.getParentId() == rootTree.getId()) {
                    if (rootTree.getChildren() == null) {
                        List<TreeDTO> myChildrens = new ArrayList<>();
                        myChildrens.add(rootTree1);
                        rootTree.setChildren(myChildrens);
                    } else {
                        rootTree.getChildren().add(rootTree1);
                    }
                }
            }
        }
        for (TreeDTO rootTree : rootTrees) {
            System.out.println(rootTree);
        }
        model.addAttribute("menuList",rootTrees);
        return "test";
    }
}
