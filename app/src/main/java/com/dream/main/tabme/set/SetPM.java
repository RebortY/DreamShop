package com.dream.main.tabme.set;

import android.app.Activity;
import android.content.Context;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginTag;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;

import org.json.JSONException;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 20:49
 */
@PresentationModel
public class SetPM extends AbstractPM {

    public final String TAG_LOGIN_OUT = "TAG_LOGIN_OUT";

    SetView baseActView;


    public SetPM(SetView baseActView1) {
        this.baseActView = baseActView1;
        DreamApplication.getApp().eventBus().register(this);
    }

    public void onClicks(ClickEvent event) {

        switch (event.getView().getId()) {

            case R.id.layoutItem_help:
                break;
            case R.id.layoutItem_fwxy:
                break;
            case R.id.layoutItem_cjwt:
                break;
            case R.id.btLogOut:
                LoginHandler.getinstance().loginOut();
                break;
        }
    }

    @Subcriber(tag = TAG_LOGIN_OUT, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


    }

    @Subcriber(tag = LoginTag.LOGIN_OUT_PHONE, threadMode = ThreadMode.MainThread)
    public void respLogout(NetResponse response) {

        baseActView.onClickView();
    }
}
