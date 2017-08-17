package com.abjf.app.http.httputil;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NN on 2017/8/12.
 */

public class RetroFitHelper{
    public static RetroFitHelper mInstance;
    public Retrofit mRetrofit;
    //本地ip为192.168.1.103
    public static final String BASE_URL = "https://api.douban.com/v2/";
    private RetroFitHelper(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(10,TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
    }
    public static RetroFitHelper getInstance(){
        if(mInstance==null){
            synchronized (RetroFitHelper.class){
                if(mInstance==null)
                    mInstance = new RetroFitHelper ();
            }
        }
        return mInstance ;
    }

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.d("zgx", "OkHttp====message " + message);
        }
    });
//interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

}