package com.abjf.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.abjf.app.entity.UserInfoEntity;
import com.abjf.app.entity.book;
import com.abjf.app.http.httputil.RetroFitHelper;
import com.abjf.app.http.service.UserService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {
private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogin();
                // 构建标准的路由请求
//                ARouter.getInstance().build("/activity/TestLoginActivity").navigation();
            }
        });
        getData();
    }
    private void getData(){
        UserService apiService = RetroFitHelper.getInstance().mRetrofit.create(UserService.class);
//        Observable<UserInfoEntity> observable = apiService.getUserInfo("sss");
        Observable<book> observable = apiService.getSearchBook("西游记", null, 0, 1);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<book>() {
                            @Override
                            public void onCompleted() {
//                                if (pd != null && pd.isShowing()) {
//                                    pd.dismiss();
//                                }
                                Log.i("end=====","====");
                                tv.setText("成功了");
                            }

                            @Override
                            public void onError(Throwable e) {
                                tv.setText(e.toString());
//                                if (pd != null && pd.isShowing()) {
//                                    pd.dismiss();
//                                }
                            }
                            @Override
                            public void onNext(book userInfoEntity) {
                                Log.i("==========","======="+userInfoEntity.getTotal()+"+++====="+userInfoEntity.getBooksList().size());
                                tv.setText(userInfoEntity.getBooksList().get(0).getTitle());
                            }

//                            @Override
//                            public void onNext(RetrofitEntity retrofitEntity) {
//                                tvMsg.setText("无封装：\n" + retrofitEntity.getData().toString());
//                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                Log.i("start=====","====");
//                                pd.show();
                            }
                        }

                );
    }
    private void getLogin(){
        UserService apiService = RetroFitHelper.getInstance().mRetrofit.create(UserService.class);
        Observable<UserInfoEntity> observable = (Observable<UserInfoEntity>) apiService.login("18682136973","123qwe","1");
//        Observable<book> observable = apiService.getSearchBook("西游记", null, 0, 1);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<UserInfoEntity>() {
                            @Override
                            public void onCompleted() {
//                                if (pd != null && pd.isShowing()) {
//                                    pd.dismiss();
//                                }
                                Log.i("end=====","====");
                                tv.setText("成功了");
                            }

                            @Override
                            public void onError(Throwable e) {
                                tv.setText(e.toString());
//                                if (pd != null && pd.isShowing()) {
//                                    pd.dismiss();
//                                }
                            }
                            @Override
                            public void onNext(UserInfoEntity userInfoEntity) {
//                                tv.setText(userInfoEntity.getBooksList().get(0).getTitle());
                            }

//                            @Override
//                            public void onNext(RetrofitEntity retrofitEntity) {
//                                tvMsg.setText("无封装：\n" + retrofitEntity.getData().toString());
//                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                Log.i("start=====","====");
//                                pd.show();
                            }
                        }

                );
    }
}
