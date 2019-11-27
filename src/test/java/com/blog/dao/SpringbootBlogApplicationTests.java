package com.blog.dao;

import com.blog.dao.ArticleDao;
import com.blog.dao.UserDao;
import com.blog.entity.*;
import com.blog.service.IndexService;
import com.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.xdevapi.JsonArray;
import groovy.grape.GrapeIvy;
import net.bytebuddy.asm.Advice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootBlogApplicationTests {

    @Autowired
    CommentDao commentDao;


    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    IndexService indexService;


    @Autowired
    TagDao tagDao;

    @Test
    public void contextLoads() {
        indexService.findAllArticles("1", "3");

//        User user = userDao.findUsernameByUsername("user");
//        System.out.println(user.toString());

//        boolean user1 = userService.userNameIsExit("user");
//        System.out.println(user1);

//        Article article = new Article();
//        article.setArticleId(Long.parseLong("1533196734"));
//        article.setAuthor("张海洋");
//        article.setOriginalAuthor("张海洋");
//        article.setArticleTitle("关于此博客，我有话哔哔");
//        article.setArticleContent("随笔感悟,原创");
//        article.setArticleType("原创");
//        article.setArticleTags("随笔感悟,原创");
//        article.setArticleCategories("我的故事");
//        article.setPublishDate("2018-07-30");
//        article.setUpdateDate("2018-07-30");
//        article.setArticleUrl("https://www.zhyocean.cn/article/1532884460");
//        article.setArticleTabloid("自我介绍我叫张海洋，紧张的张，目前是一名在校大学生，该博客的维护人，技术方向是Web后端开发，由于我大学专业是物联网工程专业，偏软硬件结合，但是众所周知，大学所培养的人才专业学习一般都得等到大二下学期乃至大三，这对于许多人来说也是个魔咒吧，好不容易经历了xx年义务教育，又历经了高考的洗礼，到了大学发现生活是如此的悠哉，没有专业课的紧张学习，难免都会如温水煮青蛙一般，陷入“舒适”的生活无法自拔。...");
//        article.setLikes(3);
//        article.setNextArticleId(Long.parseLong("1"));
//        article.setLastArticleId(Long.parseLong("3"));
//        articleDao.insert(article);

//        Article article = new Article();
//        article.setId(Integer.parseInt("1"));
//        article.setOriginalAuthor("张海洋");
//        article.setArticleTitle("自我介绍我叫张海洋，紧张的张，目前是一名在校大学生，该博客的维护人，技术方向是Web后端开发，由于我大学专业是物联网工程专业，偏软硬件结合，但是众所周知，大学所培养的人才专业学习一般都得等到大二下学期乃至大三，这对于许多人来说也是个魔咒吧，好不容易经历了xx年义务教育，又历经了高考的洗礼，到了大学发现生活是如此的悠哉，没有专业课的紧张学习，难免都会如温水煮青蛙一般，陷入“舒适”的生活无法自拔。...");
//        articleDao.updateArticleById(article);

//        Article article = articleDao.getArticleUrlById(1);
//        System.out.println(article.toString());

//        PageHelper.startPage(1,2);
//        List<Article> allArticles = articleDao.findAllArtilces();
//        for (Article Articles: allArticles
//             ) {
//            System.out.println(Articles.toString());
//        }

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        list.add(map);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", list);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        String s = jsonArray.toString();
        System.out.println(s);

//        articleDao.updateArticleLastId(Long.parseLong("1"),Long.parseLong("1532884460"));

//        articleDao.updateLikeByArticleId("1532884460");
//        indexService.findAllArticles("3","1");
    }


    @Test
    public void findAllArticles() {
        PageHelper.startPage(1, 3);
        List<Article> articles = articleDao.findAllArtilces();
        //System.out.println(articles);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        List<Map<String, Object>> newarticles = new ArrayList<>();
        Map<String, Object> map;
        for (Article article : articles) {
            map = new HashMap<>();
            map.put("thisArticleUrl", "/article/" + article.getArticleId());
            map.put("articleTags", article.getArticleTags());
            map.put("articleTitle", article.getArticleTitle());
            map.put("articleType", article.getArticleType());
            map.put("publishDate", article.getPublishDate());
            map.put("originalAuthor", article.getOriginalAuthor());
            map.put("articleCategories", article.getArticleCategories());
            map.put("articleTabloid", article.getArticleTabloid());
            map.put("likes", article.getLikes());
            newarticles.add(map);
        }
        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(newarticles);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("pageNum", pageInfo.getPageNum());
        map1.put("pageSize", pageInfo.getPageSize());
        map1.put("total", pageInfo.getTotal());
        map1.put("pages", pageInfo.getPages());
        map1.put("isFirstPage", pageInfo.isIsFirstPage());
        map1.put("isLastPage", pageInfo.isIsLastPage());
        jsonArray1.add(map1);
        System.out.println();
    }

    @Test
    public void findUserByPhone() {

//        Article article = articleDao.getActicleByActicleID(Long.parseLong("1532884460"));
//
//        System.out.println(article.toString());
//        System.out.println(article.getNextArticleId()+"      "+article.getLastArticleId());
////        indexService.getArticleByArticleId(Long.parseLong("1532884460"));
        Tag tag = new Tag();
        tag.setTagName("随便");
        tag.setTagSize(1);
        tagDao.insertTag(tag);

    }

    @Test
    public void testcommment() {
        List<Comment> fiveNewComment = commentDao.findFiveNewComment();
        for (Comment comment : fiveNewComment) {
            System.out.println(comment);
        }
    }

    @Autowired
    PermissionDao permissionDao;

    static List<Permission> resultList;

    @Test
    public void testTreeNode() {

//        Set<TreeDTO> trees = new LinkedHashSet<>();
//        TreeDTO tree1;
//
//        List<Permission> originMenus = permissionDao.findAll();
//        for (Permission originMenu : originMenus) {
//            tree1 = new TreeDTO(originMenu.getId(),originMenu.getParentId(),originMenu.getName(),originMenu.getUrl(),false,originMenu.getIcon());
//            trees.add(tree1);
//        }
//       Set<TreeDTO> rootTrees = new HashSet<>();
//        for (TreeDTO tree : trees) {
//            if (tree.getParentId() == 0){
//                rootTrees.add(tree);
//            }
//            for (TreeDTO t : trees){
//            if (t.getParentId() == tree.getId()){
//                if (tree.getChildren() == null){
//                    List<TreeDTO> myChildrens = new ArrayList<>();
//                    myChildrens.add(t);
//                    tree.setChildren(myChildrens);
//                }else {
//                    tree.getChildren().add(t);
//                }
//            }
//        }
//        }
//
//        for (TreeDTO tree : trees) {
//       //     System.out.println(tree.getName());
//        }
//        List<Permission> resultList = new ArrayList<>();
//
//        for (Permission originMenu : originMenus) {
//            if (originMenu.getParentId() == 0){
//                resultList.add(originMenu);
//
//            }
//        }
//        for (Permission permission : resultList) {
//          //  System.out.println(permission);
//        }

        Set<TreeDTO> treeDTOS = new HashSet<>();
        TreeDTO tree1;
        List<Permission> list = permissionDao.findAll();
//
//        for (Permission permission : list) {
//            System.out.println(permission.getResourceType());
//        }

        for (Permission permission : list) {
            if ("menu".equals(permission.getResourceType())) {
                tree1 = new TreeDTO(permission.getId(), permission.getParentId(), permission.getUrl(), permission.getUrl(), false, permission.getIcon());
                treeDTOS.add(tree1);
            }
        }
        for (TreeDTO treeDTO : treeDTOS) {
            //System.out.println(treeDTO);
        }

        Set<TreeDTO> rootTrees = new HashSet<>();
        for (TreeDTO rootTree : treeDTOS) {
            System.out.println(rootTree.getParentId());
            if (rootTree.getParentId() == 0) {
                rootTrees.add(rootTree);
            }
            for (TreeDTO rootTree1 : treeDTOS) {
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

    }

    public static void sortList(List<Permission> list, long id) {
        List<Permission> resultlists = new ArrayList<>();

        for (Permission permission : list) {

            if (permission.getParentId() == id) {
                resultlists.add(permission);
                sortList(list, permission.getId());
            }
        }


        for (Permission resultlist : resultlists) {
            //  System.out.println(resultlist);
        }

//        for (Permission permission : list) {
//            if (permission.getParentId() == id){
//                resultList.add(permission);
//                System.out.println("permission+"+permission.getId());
//                sortList(list,permission.getId());
//            }
//        }

    }

    @Autowired
    UserReposiory userReposiory;

    @Test
    public void elasticserachtest() {
        User userNameById = userDao.findUserNameById(1);
        System.out.println(userNameById);
    }

}
