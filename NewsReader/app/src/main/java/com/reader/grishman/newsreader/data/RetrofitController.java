package com.reader.grishman.newsreader.data;

import com.reader.grishman.newsreader.BuildConfig;
import com.reader.grishman.newsreader.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Singleton to manage retrofit
 */

public class RetrofitController {
    private static final String API_BASE_URL = Constants.BASE_URL;
    private static final String TAG = RetrofitController.class.getSimpleName();
    private static volatile RetrofitController mInstance;
    private static Retrofit mClient;

    private RetrofitController() {
        mClient = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getOkHttpClient())
                .baseUrl(API_BASE_URL)
                .build();
    }

    public static RetrofitController getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitController.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitController();
                }
            }
        }
        return mInstance;
    }

    private static OkHttpClient getOkHttpClient() {
        try {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set log level
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("X-Api-Key", BuildConfig.API_KEY);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(headerInterceptor);
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(15, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(true);
            builder.addInterceptor(logging);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to return rest service
     *
     * @param serviceClass Name of api service
     * @return New instance of api service
     */
    public <S> S createService(Class<S> serviceClass) {
        return mClient.create(serviceClass);
    }

    public Retrofit getClient() {
        return mClient;
    }
}

