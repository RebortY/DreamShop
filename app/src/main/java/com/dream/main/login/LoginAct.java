package com.dream.main.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dream.R;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

import android.widget.Toast;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 17:03
 * 登录界面
 */
public class LoginAct extends Activity implements LoginView {

    LoginPM loginPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPM = new LoginPM(this);
        ViewBinder viewBinder = createViewBinder();
        View rootView = viewBinder.inflateAndBind(R.layout.activity_login, loginPM);
        setContentView(rootView);

        getLoginPM();
    }

    public LoginPM getLoginPM() {
        return loginPM;
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

    private ViewBinder createViewBinder() {
        BinderFactory reusableBinderFactory = new BinderFactoryBuilder().build();
        return reusableBinderFactory.createViewBinder(this);
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

    @Subcriber(tag = LoginTag.LOGIN , threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp){
        if(RespCode.SUCCESS.equals(resp.getErrorCode())){
            ToastUtil.show(this, "登录成功");
        }else{
            ToastUtil.show(this, resp.getErrorMsg());
        }
    }

}
