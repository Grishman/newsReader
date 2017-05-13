package com.reader.grishman.newsreader.data;

import com.reader.grishman.newsreader.model.ArticlesResponse;
import com.reader.grishman.newsreader.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API methods
 */

public interface NewsApi {

    @GET(Constants.ARTICLES)
    Call<ArticlesResponse> getNews(@Query(Constants.QUERY_SOURCE) String source, @Query(Constants.QUERY_SORT_BY) String sortBy);
}
