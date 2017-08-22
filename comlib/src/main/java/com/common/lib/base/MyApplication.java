package com.common.lib.base;

import android.app.Application;

import com.bluelinelabs.conductor.rxlifecycle.RxController;


/**
 * Created by NN on 2017/8/12.
 */

public class MyApplication extends Application{
    private static MyApplication myApplication = null;
    private RxController rxController;
    @Override
    public void onCreate() {
        myApplication = this;
        super.onCreate();
    }
    public static MyApplication getInstance() {
        if (null == myApplication) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }
    public RxController getRxController() {
        return rxController;
    }

    public void setRxController(RxController rxController) {
        this.rxController = rxController;
    }
}
