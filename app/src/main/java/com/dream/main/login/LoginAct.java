package com.dream.main.login;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;

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

    }

    @Override
    public void setOnClickView(View view) {
        switch (view.getId()) {
            case R.id.tv_user_reg:
                startActivity(new Intent(this, RegAct.class));
                break;
            case R.id.bt_login:
                if (isCheckText()) {
                    LoginHandler.getinstance().login(loginPM.getUserName(), loginPM.getUserPsd());
                }
                break;
        }
    }

    private boolean isCheckText() {

        if (StringUtils.isEmpty(loginPM.getUserName())) {
            ToastUtil.show(this, R.string.tv_username_empty);
            return false;
        }

        if (StringUtils.isEmpty(loginPM.getUserPsd())) {
            ToastUtil.show(this, R.string.tv_psd_empty);
            return false;
        }
        return true;
    }


    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
            finish();
        } else {
            ToastUtil.show(this, resp.getErrorMsg());
        }
    }

}
