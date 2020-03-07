package com.jere.test.article.modle.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author jere
 */
@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "url")
    public String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
