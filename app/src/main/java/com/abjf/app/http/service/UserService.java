package com.abjf.app.http.service;

import com.abjf.app.entity.UserInfoEntity;
import com.abjf.app.entity.book;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by NN on 2017/8/12.
 */

public interface UserService {


    public static final String Base_URL = "http://ip.taobao.com/";
    /**
     *普通写法
     */
    @GET("service/getIpInfo.php/")
    Observable<ResponseBody> getData(@Query("ip") String ip);


    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);


    @POST("{url}")
    Observable<ResponseBody> executePost(
            @Path("url") String url,
            @FieldMap Map<String, String> maps);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<UserInfoEntity> getUserInfo(@Body String name);

    @GET("book/search")
    Observable<book> getSearchBook(@Query("q") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);
//    @POST("users/new")
//    Call<UserInfoEntity> login(@Body String mobile,@Body String password,@Body String logintype);

    @FormUrlEncoded
    @POST("{root}/{path}")
    Observable<UserInfoEntity> login(@Path("root") String mobile, @Path("path") String password,@Path("path") String logintype);

//    @Multipart
//    @POST("{url}")
//    Observable<ResponseBody> upLoadFile(
//            @Path("url") String url,
//            @Part("image\\"; filename=\\"image.jpg") RequestBody avatar);
//
//    @POST("{url}")
//    Call<ResponseBody> uploadFiles(
//            @Url("url") String url,
//            @Part("filename") String description,
//            @PartMap()  Map<String, RequestBody> maps);

}


