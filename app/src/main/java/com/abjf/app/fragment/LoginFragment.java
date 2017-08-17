package com.abjf.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abjf.app.R;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = "/fragment/login")
public class LoginFragment extends Fragment {


    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
