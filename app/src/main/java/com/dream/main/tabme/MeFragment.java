package com.dream.main.tabme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dream.R;
import com.dream.bean.UpLoadHeadBean;
import com.dream.main.AbstractTabFragment;
import com.dream.main.DreamApplication;
import com.dream.main.goodpay.GoodPayPM;
import com.github.snowdream.android.util.Log;

import butterknife.Bind;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/16.
 */
public class MeFragment extends AbstractTabFragment implements MeFragmentView {

    public static final int ACT_RE_CODE = 1001;

    @Bind(R.id.layout_un_login)
    LinearLayout layout_login_un;

    @Bind(R.id.layout_login)
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

    @Override
    public void setOnClickView(View view) {

        if(view.getId() == R.id.img_hand){
            startActivityForResult(new Intent(getActivity(), UserInfoAct.class), ACT_RE_CODE);

        }
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
        layout_login_un = (LinearLayout) rootView.findViewById(R.id.layout_un_login);
        layout_login = (LinearLayout) rootView.findViewById(R.id.layout_login);

        return rootView;
    }

    /**
     *
     * @param type  0未登录  1登录
     */
    @Override
    public void onClickView(int type) {
        if(type == 0){
            layout_login_un.setVisibility(View.VISIBLE);
            layout_login.setVisibility(View.GONE);
        }else {
            layout_login_un.setVisibility(View.GONE);
            layout_login.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 处理支付成功刷新UI
     *
     * @param
     */
    @Subcriber(tag = GoodPayPM.TAG_SHOP_ALIPAY_OK, threadMode = ThreadMode.MainThread)
    public void respHandlerPay() {
        onResume();
    }

    /**
     * 修改头像resp
     *
     * @param
     */
    @Subcriber(tag = UserInfoPM.CODE_HEAD_OK_POST, threadMode = ThreadMode.MainThread)
    public void postResp(UpLoadHeadBean handBeans) {
        Log.d("更新成功222");
        mePM.changeSupport.firePropertyChange("url");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ACT_RE_CODE){
            mePM.goLogin();
        }
    }
}
