package com.abjf.app.http.httputil;

import android.util.Log;

import com.abjf.app.common.Common;
import com.common.lib.utils.BasicParamsInterceptor;
import com.common.lib.utils.HttpsUtils;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NN on 2017/8/12.
 */

public class RetroFitHelper{
    public static RetroFitHelper mInstance;
    public Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    //本地ip为192.168.1.103
    public static final String BASE_URL = "https://api.douban.com/v2/";
    private RetroFitHelper() {


//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.connectTimeout(10, TimeUnit.SECONDS);
//        httpClient.readTimeout(10, TimeUnit.SECONDS);
//
//        mRetrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(httpClient.addInterceptor(interceptor).build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();


        HttpLoggingInterceptor.Logger logger=new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if(Common.isDebug)
                    Log.i("HttpLogging=====",message);
            }
        };
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);

        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        BasicParamsInterceptor basicInterceptor = new BasicParamsInterceptor.Builder().build();

        if (Common.isDebug)
        {
            mOkHttpClient = new OkHttpClient.Builder().sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null)).hostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    //强行返回true 即验证成功
                    return true;
                }
            }).connectionSpecs(Collections.singletonList(getConnectionSpec()))
                    .retryOnConnectionFailure(true)
                    .readTimeout(60000, TimeUnit.MILLISECONDS)
                    .connectTimeout(60000, TimeUnit.MILLISECONDS)
                    .addInterceptor(basicInterceptor)
                    .addInterceptor(interceptor)
                    .build();
        }else
        {
            mOkHttpClient = new OkHttpClient.Builder().sslSocketFactory(HttpsUtils.getSslSocketFactory(new Buffer().writeUtf8("加载HTTPSKey").inputStream(), null, null))
                    .connectionSpecs(Collections.singletonList(getConnectionSpec()))
                    .retryOnConnectionFailure(true)
                    .readTimeout(60000, TimeUnit.MILLISECONDS)
                    .connectTimeout(60000, TimeUnit.MILLISECONDS)
                    .addInterceptor(basicInterceptor)
                    .build();
            // .addInterceptor(interceptor)

        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }
    private static ConnectionSpec getConnectionSpec() {
        //http://blog.csdn.net/cui130/article/details/51759098
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).cipherSuites(CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA).build();
        return spec;
    }
    public static RetroFitHelper getInstance(){
        if(mInstance==null){
            synchronized (RetroFitHelper.class){
                if(mInstance==null)
                    mInstance = new RetroFitHelper ();
            }
        }
        return mInstance;
    }


}