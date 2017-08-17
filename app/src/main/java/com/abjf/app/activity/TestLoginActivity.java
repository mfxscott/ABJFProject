package com.abjf.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.abjf.app.R;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/activity/TestLoginActivity")
public class TestLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
    }
}
