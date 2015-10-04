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

    LoginPM loginPM;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Object initPM() {
        if (loginPM == null){
            loginPM = new LoginPM(this, this);
        }
        return loginPM;
    }

    @Override
    public void setOnClickView(View view) {

        switch (view.getId()){

            case R.id.tv_user_reg:
                startActivity(new Intent(this, RegAct.class));
                break;
            case R.id.tv_check_psw:
                startActivity(new Intent(this, PsdForgetAct.class));
                break;
        }
    }

    @Override
    public void setOnActClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }


    /**
     * 已安装QQ登录
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        loginPM.mTencent.onActivityResultData(requestCode, resultCode, data, loginPM.loginListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginPM.loginListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
