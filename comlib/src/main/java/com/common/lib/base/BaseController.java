package com.common.lib.base;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.rxlifecycle.RxController;

public abstract class BaseController extends RxController {
    // private boolean hasExited;
    public Activity mactivity;
    public Toolbar toolbar;
    public BaseController() {
    }

    public BaseController(Bundle args) {
        super(args);
    }


    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mactivity = (Activity) getActivity();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);// 清楚禁止截屏
        MyApplication.getInstance().setRxController(this);
        initView(view);
        initData();
        return view;
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
    }

    @Override
    protected void onActivityResumed(Activity activity) {
        MyApplication.getInstance().setRxController(this);
        super.onActivityResumed(activity);
    }

//    public void showFrameDialog(String str) {
//        showFrameDialog(str, true);
//    }
//
//    public void showFrameDialog(String tip, Boolean cancelAble) {
//        dialogUtil.showFrameDialog(mactivity, tip, cancelAble);
//    }
//
//    @Override
//    protected void onDetach(@NonNull View view) {
//        super.onDetach(view);
//        dialogUtil.closeFrameDialog();
//    }

//    // 关闭环形进度条
//    public void closeFrameDialog() {
//        dialogUtil.closeFrameDialog();
//    }
//
//    public void showFrameDialog() {
//        dialogUtil.showFrameDialog(mactivity);
//    }
//
//
//    public void showToast(int tip) {
//        dialogUtil.showToast(mactivity, tip);
//    }
//
//    public void showToast(String tip) {
//        dialogUtil.showToast(mactivity, tip);
//    }
//
//    public void cancelToast() {
//        dialogUtil.cancelToast();
//    }

    protected abstract void initView(@NonNull View view);

    protected abstract void initData();

    protected abstract int getLayoutId();



    public void pushController(BaseController baseController) {
        getRouter().pushController(RouterTransaction.with(baseController));
        //.pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
    }

    public void pushAndPopController(BaseController baseController) {
        getRouter().pushController(RouterTransaction.with(baseController));
        getRouter().popController(this);
        //    .pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));

    }


    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
    }

}
