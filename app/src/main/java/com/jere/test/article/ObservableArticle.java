package com.jere.test.article;

import android.databinding.BaseObservable;

import com.jere.test.BR;

public class ObservableArticle extends BaseObservable {
    private String author;
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
//        notifyPropertyChanged();
    }

    public void setUrl(String url) {
        this.url = url;
//        notifyPropertyChanged(BR.article);
    }

    public String getUrl() {
        return url;
    }
}
