package com.reader.grishman.newsreader.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.reader.grishman.newsreader.data.NewsApi;
import com.reader.grishman.newsreader.data.RetrofitController;
import com.reader.grishman.newsreader.data.events.ArticlesEvent;
import com.reader.grishman.newsreader.model.ArticlesResponse;
import com.reader.grishman.newsreader.model.BaseApiCallback;
import com.reader.grishman.newsreader.model.BaseResponse;
import com.reader.grishman.newsreader.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;


/**
 * Created by grishman on 13.05.17.
 */

public class ArticlesFetcher extends IntentService {

    private Call<ArticlesResponse> call;

    public ArticlesFetcher() {
        super("test");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String source = intent.getStringExtra(Constants.QUERY_SOURCE);
            String sortBy = intent.getStringExtra(Constants.QUERY_SORT_BY);
            getArticles(source, sortBy);
        }
    }

    private void getArticles(String source, String sortBy) {
        call = RetrofitController.getInstance().createService(NewsApi.class).getNews(source, sortBy);
        call.enqueue(new BaseApiCallback<ArticlesResponse>() {
            @Override
            public void onSuccess(@NonNull ArticlesResponse body) {
                //todo notify UI and save to DB
                EventBus.getDefault().postSticky(new ArticlesEvent(body.getArticles()));
            }

            @Override
            public void onFailed(@Nullable BaseResponse.Error error, @Nullable Throwable t) {
                //todo notify
            }
        });
    }
}
