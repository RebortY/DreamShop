package com.dream.main.tabme;

import android.content.Intent;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.DreamApplication;
import com.dream.main.login.LoginAct;
import com.dream.main.login.RegAct;
import com.dream.main.tabpublish.PublishPM;
import com.dream.net.NetResponse;
import com.dream.net.business.login.LoginTag;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/16.
 */
public class MeFragment extends AbstractTabFragment implements MeFragmentView{

    MEPM mePM;

    public MeFragment() {

        mePM = new MEPM(this);
        DreamApplication.getApp().eventBus().register(this);
    }

    @Override
    public Object getPM() {
        return new MEPM(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }

    //处理网络层回调 , 可以直接操作UI ， 但是不能做超时的工作
    @Subcriber(tag = LoginTag.LOGIN , threadMode = ThreadMode.MainThread)
    public void handResp(NetResponse response){

    }

    @Override
    public void setOnClickId(int id) {
        switch (id){
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginAct.class));
                break;
            case R.id.tv_reg:
                startActivity(new Intent(getActivity(), RegAct.class));
                break;
        }
    }

}
