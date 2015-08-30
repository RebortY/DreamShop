package com.dream.main.login;

import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.MainActivity;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.qq.BaseUiListener;
import com.dream.qq.QQConfig;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import org.json.JSONObject;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 17:03
 * 登录界面
 */
public class LoginAct extends BaseActivity implements BaseActView {

    LoginPM loginPM;
    Tencent mTencent;

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
    }

    @Override
    public void setOnClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_user_reg:
                startActivity(new Intent(this, RegAct.class));
                break;
            case R.id.bt_login:
                if (isCheckText()) {
                    LoginHandler.getinstance().login(LoginHandler.LOGINHANDLER, loginPM.getUserName(), loginPM.getUserPsd());
                }
                break;
            case R.id.imageView3:
                mTencent = Tencent.createInstance(QQConfig.QQ_AppId, this);
                if (!mTencent.isSessionValid()) {
                    mTencent.loginServerSide(this, "all", loginListener);
                }
                break;
        }
    }

    private boolean isCheckText() {

        if (StringUtils.isEmpty(loginPM.getUserName())) {
            ToastUtil.show( R.string.tv_username_empty);
            return false;
        }

        if (StringUtils.isEmpty(loginPM.getUserPsd())) {
            ToastUtil.show( R.string.tv_psd_empty);
            return false;
        }
        return true;
    }


    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
            startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
    }

    /**
     * 未安装QQ登录
     */
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {

            Log.d("11111＊＊＊＊＊＊＊QQ登录返回＝" + values);
            ToastUtil.show("1");
            initOpenidAndToken(values);
            startActivity(new Intent(LoginAct.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    };

    /**
     * 已安装QQ登录
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastUtil.show("2");
        Log.d("2222＊＊＊＊＊＊＊QQ登录返回＝" + "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        mTencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        if(requestCode == Constants.REQUEST_API) {
            Tencent.handleResultData(data, loginListener);
            startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }

}
