package com.dream.main.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.dream.main.DreamApplication;

import butterknife.ButterKnife;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/26 23:47
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setAttIntent(intent);
        if (initPM() == null) {
            setContentView(getLayoutId());
        } else {
            View view = DreamApplication.getApp().inflateViewAndBind(this, getLayoutId(), initPM());
            setContentView(view);
            ButterKnife.bind(this);
        }
        initView();
    }

    public abstract int getLayoutId();

    public abstract Object initPM();

    public void initView(){}

    public Object getPM() {
        return initPM();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void setAttIntent(Intent intent){};
}
