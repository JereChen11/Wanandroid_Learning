package com.jere.test.article.roomdatabase;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
