package com.dream.main.login;

import android.content.Intent;
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
import com.dream.qq.QQUtils;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;
import com.tencent.connect.UserInfo;
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

                if (!mTencent.isSessionValid()) {
                    mTencent.loginServerSide(this, "all", loginListener);
                }
                break;
            case R.id.login_out:
                if (mTencent.isSessionValid()) {
                    logout();
                }
            case R.id.qq_share:
//                onClickStory();
                if (QQUtils.ready(mTencent, LoginAct.this)) {
                    UserInfo mInfo = new UserInfo(LoginAct.this, mTencent.getQQToken());
                    mInfo.getUserInfo(new BaseUiListener(LoginAct.this, "get_simple_userinfo") {
                        @Override
                        protected void doComplete(JSONObject values) {
                        ToastUtil.show("$$$$$$$" + values.toString());
                        }
                    });
                }


//                QQUtils.doShareToQQ(this, mTencent);

                break;
        }
    }

    private boolean isCheckText() {

        if (StringUtils.isEmpty(loginPM.getUserName())) {
            ToastUtil.show(R.string.tv_username_empty);
            return false;
        }

        if (StringUtils.isEmpty(loginPM.getUserPsd())) {
            ToastUtil.show(R.string.tv_psd_empty);
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
//            QQUtils.initOpenidAndToken(mTencent, values);

            finish();

//            getUserInfoInThread();

        }
    };

    /**
     * 已安装QQ登录
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastUtil.show("2");
        Log.d("2222＊＊＊＊＊＊＊QQ登录返回＝" + "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        mTencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        if (requestCode == Constants.REQUEST_API) {
            Tencent.handleResultData(data, loginListener);
//            startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 同步调用QQ用户信息
     */
    public void getUserInfoInThread() {
        new Thread() {
            @Override
            public void run() {
//                JSONObject json = null;
//                try {
//                    json = mTencent.request("get_simple_userinfo", null, Constants.HTTP_GET);
//                    Log.d("QQ用户信息＝" + json);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (HttpUtils.NetworkUnavailableException e) {
//                    e.printStackTrace();
//                } catch (HttpUtils.HttpStatusException e) {
//                    e.printStackTrace();
//                }

            }
        }.start();
    }

    /**
     * QQ退出登录
     * 将来放到退出登陆的UI
     */
    public void logout() {
        mTencent.logout(this);
    }


}
