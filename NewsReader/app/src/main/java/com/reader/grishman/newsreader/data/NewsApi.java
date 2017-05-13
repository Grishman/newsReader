package com.reader.grishman.newsreader.data;

import com.reader.grishman.newsreader.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * API methods
 */

public interface NewsApi {

    @GET(Constants.ARTICLES)
    Call<ResponseBody> getNews();
}
