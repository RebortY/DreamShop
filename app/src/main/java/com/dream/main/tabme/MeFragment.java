package com.dream.main.tabme;

import android.widget.Toast;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.DreamApplication;
import com.dream.main.tabmain.TabMainPM;
import com.dream.net.NetResponse;
import com.dream.net.business.login.LoginTag;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/16.
 */
public class MeFragment extends AbstractTabFragment {


    public MeFragment() {
        DreamApplication.getApp().eventBus().register(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public Object getPM() {
        return new MEPM("我的");
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }


    //处理网络层回调 , 可以直接操作UI ， 但是不能做超时的工作
    @Subcriber(tag = LoginTag.LOGIN , threadMode = ThreadMode.MainThread)
    public void handResp(NetResponse response){

    }

}
