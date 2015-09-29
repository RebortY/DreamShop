package com.dream.main.login;

import android.content.Context;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 20:12
 */
@PresentationModel
    public class LoginPM extends TitleBarPM {

    Context mContext;

//    String userName = "18947102346";
//
//    String userPsd = "18947102346";

    String userName;

    String userPsd;

    LoginView loginView;

    public LoginPM(Context context, LoginView loginViews) {
        DreamApplication.getApp().eventBus().register(this);
        this.loginView = loginViews;
        this.mContext = context;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public void onClicks(ClickEvent event) {
        loginView.setOnClickView(event.getView());

        switch (event.getView().getId()) {
            case R.id.bt_login:
                if (isCheckText()) {
                    LoginHandler.getinstance().login(LoginHandler.LOGIN_PHONE, getUserName(), getUserPsd());
                }
                break;
        }
    }

    private boolean isCheckText() {

        if (DreamUtils.isEmpty(getUserName())) {
            ToastUtil.show(R.string.tv_username_empty);
            return false;
        }

        if (DreamUtils.isEmpty(getUserPsd())) {
            ToastUtil.show(R.string.tv_psd_empty);
            return false;
        }
        return true;
    }

    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
            loginView.setOnActClick();
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
    }

    @Override
    public String getTitleBar() {
        return mContext.getResources().getString(R.string.tv_login);
    }
}
