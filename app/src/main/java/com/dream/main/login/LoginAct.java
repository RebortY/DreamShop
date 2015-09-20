package com.dream.main.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.qq.BaseUiListener;
import com.dream.qq.QQConfig;
import com.dream.qq.QQUtils;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 17:03
 * 登录界面
 */
public class LoginAct extends BaseActivity implements LoginView {

    public static String QQ_HEAD_URL = null;

    LoginPM loginPM;

    UserInfo mInfo;

    public Tencent mTencent = null;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Object initPM() {
        loginPM = new LoginPM(this);
        return loginPM;
    }

    @Override
    public void initView() {
        DreamApplication.getApp().eventBus().register(this);

        mTencent = Tencent.createInstance(QQConfig.QQ_AppId, this);
    }

    @Override
    public void setOnClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_user_reg:
                startActivity(new Intent(this, RegAct.class));
                break;
            case R.id.bt_login:
                if (isCheckText()) {
                    LoginHandler.getinstance().login(LoginHandler.LOGIN_PHONE, loginPM.getUserName(), loginPM.getUserPsd());
                }
                break;
            case R.id.imageView3:

                if (!mTencent.isSessionValid()) {
                    mTencent.login(this, "all", loginListener);
                }
                break;
            case R.id.login_out:
                if (mTencent.isSessionValid()) {
                    logout();
                }
                break;
            case R.id.qq_share:
                QQUtils.doShareToQQ(this, mTencent);
                break;
        }
    }

    @Override
    public void setOnActClick() {

    }

    private boolean isCheckText() {

        if (DreamUtils.isEmpty(loginPM.getUserName())) {
            ToastUtil.show(R.string.tv_username_empty);
            return false;
        }

        if (DreamUtils.isEmpty(loginPM.getUserPsd())) {
            ToastUtil.show(R.string.tv_psd_empty);
            return false;
        }
        return true;
    }


    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
            finish();
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
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

    /**
     * 已安装QQ登录
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * QQ退出登录
     * 将来放到退出登陆的UI
     */
    public void logout() {
        mTencent.logout(this);
    }

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
        mInfo = new UserInfo(LoginAct.this, mTencent.getQQToken());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    /**
     * QQ登录后
     *
     * @param resp
     */
    @Subcriber(tag = LoginTag.LOGIN_QQ, threadMode = ThreadMode.MainThread)
    public void loginRespHandlerQQ(LoginResp resp) {
        finish();
    }

}
