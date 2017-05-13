package com.reader.grishman.newsreader.model;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Articles response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticlesResponse extends BaseResponse {

    @JsonProperty("articles")
    private List<Article> articles;

    @Nullable
    public List<Article> getArticles() {
        return articles;
    }
}
