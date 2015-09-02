package com.dream.main.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.MainPM;
import com.dream.qq.QQConfig;
import com.tencent.tauth.Tencent;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

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
        if (initPM() == null) {
            setContentView(getLayoutId());
        } else {
            View view = DreamApplication.getApp().inflateViewAndBind(this, getLayoutId(), initPM());
            setContentView(view);
            ButterKnife.bind(this);
        }
        initView();

    }

    private ViewBinder createViewBinder() {
        BinderFactory reusableBinderFactory = new BinderFactoryBuilder().build();
        return reusableBinderFactory.createViewBinder(this);
    }

    public abstract int getLayoutId();

    public abstract Object initPM();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //切记在 onDestroy 的时候，取消注册。 否则会造成内存泄露 ，在Fragment 中，如果fragment 关闭也要执行此方法
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }


}
