package com.abjf.app.http.httputil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class HttpUtils{
    public Retrofit mRetrofit;

    public HttpUtils(String baseUrl, Interceptor interceptor){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//          .addConverterFactory(JsonConverterFactory.create())
                .client(httpClient.addInterceptor(interceptor).build())
                .build();
    }

    public <T> T getServer(Class<T> clazz){
        return mRetrofit.create(clazz);
    }
}