package com.journal.dao;

import com.journal.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 根据搜索词查询书籍以||分割（科幻||玄幻）
     * @param searchWord 搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE (userID IN (SELECT userID FROM user WHERE nickName LIKE CONCAT('%', #{searchWord}, '%'))",
            "OR title LIKE CONCAT('%', #{searchWord}, '%')",
            "OR keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "OR categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%')))",
            "AND a.state = 3"
    })
    List<Article> findArticles(@Param("searchWord") String searchWord);

    /**
     * 根据作者搜索文章
     * @param searchWord 作者昵称搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE (u.nickName LIKE CONCAT('%', #{searchWord}, '%'))",
            "AND a.state = 3"
    })
    List<Article> findByAuthor(@Param("searchWord") String searchWord);

    /**
     * 根据类别搜索文章
     * @param searchWord 类别名称搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE c.categoryID IN (SELECT categoryID FROM category WHERE categoryName LIKE CONCAT('%', #{searchWord}, '%'))",
            "AND a.state = 3"
    })
    List<Article> findByCategory(@Param("searchWord") String searchWord);

    /**
     * 根据关键词搜索文章
     * @param searchWord 关键词搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE keywords LIKE CONCAT('%', #{searchWord}, '%')",
            "AND a.state = 3"
    })
    List<Article> findByKeyWord(@Param("searchWord") String searchWord);

    /**
     * 根据题目搜索文章
     * @param searchWord 题目搜索词
     * @return 符合条件的文章列表
     */
    @Select({
            "SELECT a.*, u.nickName, c.categoryName",
            "FROM article a",
            "JOIN user u ON a.userID = u.userID",
            "JOIN category c ON a.categoryID = c.categoryID",
            "WHERE title LIKE CONCAT('%', #{searchWord}, '%')",
            "AND a.state = 3"
    })
    List<Article> findByTitle(@Param("searchWord") String searchWord);


    /**
     * 根据id查询文章
     * @param articleId 文章ID
     * @return 对应ID的文章对象
     */
    @Select("SELECT a.*, u.nickName, c.categoryName FROM article a JOIN user u ON a.userID = u.userID JOIN category c ON a.categoryID = c.categoryID WHERE a.articleId = #{articleId} AND a.state = 3")
    Article findArticleById(@Param("articleId") int articleId);

    /**
     * 查询所有文章，并按照更新时间降序排序
     * @return 所有文章列表
     */
    @Select("SELECT a.*, u.nickName, c.categoryName FROM article a JOIN user u ON a.userID = u.userID JOIN category c ON a.categoryID = c.categoryID WHERE a.state = 3 ORDER BY updateTime DESC")
    List<Article> findAllArticlesOrderByUpdateTime();
}