package com.dream.main.tabme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.login.LoginTag;

import butterknife.Bind;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/16.
 */
public class MeFragment extends AbstractTabFragment implements MeFragmentView {

    @Bind(R.id.layout_un_login)
    LinearLayout layout_login;

    MEPM mePM;

    public MeFragment() {

        mePM = new MEPM(getActivity(), this);

    }

    @Override
    public Object getPM() {
        return new MEPM(getActivity(), this);
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
    public void setOnClickView(View view) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  super.onCreateView(inflater, container, savedInstanceState);
        layout_login = (LinearLayout) rootView.findViewById(R.id.layout_un_login);
        return rootView;
    }

    @Override
    public void onClickView() {

        layout_login.setVisibility(View.GONE);
    }

}
