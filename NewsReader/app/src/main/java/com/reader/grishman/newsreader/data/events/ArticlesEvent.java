package com.reader.grishman.newsreader.data.events;

import com.reader.grishman.newsreader.model.Article;

import java.util.List;

/**
 * Created by grishman on 13.05.17.
 */

public class ArticlesEvent {
    private List<Article> articleList;

    public ArticlesEvent(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
