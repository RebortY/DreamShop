package com.dream.main.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;
import com.dream.main.DreamApplication;

import butterknife.ButterKnife;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 21:00
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = DreamApplication.getApp().inflateViewAndBind(getActivity(), getlayoutId(), initPM());
        ButterKnife.bind(getActivity());

        initView();
        return view;
    }

    public  abstract  int getlayoutId();

    public abstract Object initPM();

    public void initView(){};

    public Object getPM() {
        return initPM();
    }
}
