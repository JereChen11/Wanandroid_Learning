package com.jere.test.article.modle.roomdatabase;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author jere
 */
public interface IArticleDao {

    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Insert
    void insertArticle(Article article);

    @Insert
    void insertArticles(Article... articles);

    @Update
    void updateArticle(Article article);

    @Update
    void updateArticles(Article... articles);

    @Delete
    void deleteArticle(Article article);

    @Delete
    void deleteArticles(Article... articles);
}
