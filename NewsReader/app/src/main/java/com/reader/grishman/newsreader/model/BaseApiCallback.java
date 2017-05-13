package com.reader.grishman.newsreader.model;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.reader.grishman.newsreader.data.RetrofitController;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Adapter for retrofit callback witch created to intercept responses.
 *
 * @param <T> expected response type
 */
public abstract class BaseApiCallback<T extends BaseResponse> implements Callback<T> {

    public BaseApiCallback() {

    }

    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            BaseResponse baseResponse = response.body();
            if (baseResponse.isSuccess()) {
                onSuccess(response.body());
            }
        } else {
            Converter<ResponseBody, BaseResponse.Error> converter =
                    RetrofitController.getInstance().getClient()
                            .responseBodyConverter(BaseResponse.Error.class, new Annotation[0]);
            BaseResponse.Error error;
            try {
                error = converter.convert(response.errorBody());
                onFailed(error, null);
            } catch (IOException e) {
                onFailure(null, new NetworkErrorException("Failed to get error body ", e));
            }
        }

    }

    public final void onFailure(Call<T> call, Throwable t) {
        onFailed(null, t);
    }

    public abstract void onSuccess(@NonNull T body);


    public abstract void onFailed(@Nullable BaseResponse.Error error, @Nullable Throwable t);
}