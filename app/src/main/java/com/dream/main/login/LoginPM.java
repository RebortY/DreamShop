package com.dream.main.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.qq.BaseUiListener;
import com.dream.qq.QQConfig;
import com.dream.qq.QQUtils;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;
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

    public static String QQ_HEAD_URL = null;

    public static Tencent mTencent = null;

    UserInfo mInfo;

    Activity mContext;

//    String userName = "18947102346";
//
//    String userPsd = "18947102346";

    String userName;

    String userPsd;

    LoginView loginView;

    public LoginPM(Activity context, LoginView loginViews) {

        mTencent = Tencent.createInstance(QQConfig.QQ_AppId, context);
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
            case R.id.imageView3:

                if (!mTencent.isSessionValid()) {
                    mTencent.login(mContext, "all", loginListener);
                }
                break;
            case R.id.qq_share:
                QQUtils.doShareToQQ(mContext, mTencent);
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


    /**
     * 未安装QQ登录
     */
    IUiListener loginListener = new BaseUiListener(0) {
        @Override
        protected void doComplete(int tag, JSONObject values) {

            Log.d("登录" + values.toString());
            if (tag == 0) {
                JSONObject jo = (JSONObject) values;
                try {
                    int ret = jo.getInt("ret");
                    if (ret == 0) {
                        initOpenidAndToken(mTencent, values);
                        getUserInfo(jo.getString("openid"));
                    } else {
                        Log.d("QQ登录失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("QQ登录失败");
                }
            }
        }
    };


    public void initOpenidAndToken(Tencent mTencent, JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private void getUserInfo(final String openid) {
        mInfo = new UserInfo(mContext, mTencent.getQQToken());
        mInfo.getUserInfo(new BaseUiListener(1) {
            @Override
            protected void doComplete(int tag, JSONObject values) {
                if (tag == 1) {
                    try {
                        JSONObject jsonObject = (JSONObject) values;
                        int ret = 0;
                        ret = jsonObject.getInt("ret");
                        if (ret == 0) {
                            String nickname = jsonObject.getString("nickname");
                            QQ_HEAD_URL = jsonObject.getString("figureurl_qq_2");
                            LoginHandler.getinstance().login(LoginHandler.LOGIN_QQ, openid, nickname);
                        } else {
                            Log.d("QQ登录失败");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * QQ登录后
     *
     * @param resp
     */
    @Subcriber(tag = LoginTag.LOGIN_QQ, threadMode = ThreadMode.MainThread)
    public void loginRespHandlerQQ(LoginResp resp) {
        loginView.setOnActClick();
    }
}
