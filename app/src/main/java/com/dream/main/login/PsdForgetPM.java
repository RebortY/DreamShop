package com.dream.main.login;

import android.content.Context;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/30 11:48
 */
@PresentationModel
public class PsdForgetPM extends TitleBarPM{

    private final String TYPE_SMS_CODE = "TYPE_SMS_CODE"; //验证码
    private final String TYPE_COMMIT = "TYPE_COMMIT"; //提交


    Context mContext;

    PsdForgetView view;

    String userPhone;

    String smsCode;

    String psd;

    PsdForgetPM(Context context, PsdForgetView views){
        this.view = views;
        this.mContext = context;
        setTitleBar(mContext.getResources().getString(R.string.tv_check_password));
        DreamApplication.getApp().eventBus().register(this);
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public void onClicks(ClickEvent event) {

        switch (event.getView().getId()) {
            case R.id.bt_get_code:
                getSmsCodeData();
                break;
            case R.id.bt_commit:
                if (isChickText()) {
                    getPassword();
                }
                break;
        }
    }

    /**
     * get短信验证码
     */
    private void getSmsCodeData() {
        if (DreamUtils.isEmpty(getUserPhone())) {
            ToastUtil.show(R.string.tv_input_phone);
        } else {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("phone", getUserPhone());
            DreamApplication.getApp().getDreamNet().netJsonPost(TYPE_SMS_CODE, ProtocolUrl.SMS_CODE_FORGET, params);
        }
    }

    /**
     * 短信验证码
     *
     * @param response
     */
    @Subcriber(tag = TYPE_SMS_CODE, threadMode = ThreadMode.MainThread)
    public void smsCodeResponse(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
//                JSONObject jsonObj = (JSONObject) response.getResp();
                view.setOnActClick(2);
            } catch (Exception ex) {
                ToastUtil.show("短信验证码获取失败");
            }
        }
    }

    /**
     * 找回密码
     */
    private void getPassword() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", getUserPhone());
        params.put("code", getSmsCode());
        params.put("password", getPsd());
        DreamApplication.getApp().getDreamNet().netJsonPost(TYPE_COMMIT, ProtocolUrl.SMS_PASSWORD_RESET, params);
    }

    /**
     * 找回密码提交后结果处理
     */
    @Subcriber(tag = TYPE_COMMIT, threadMode = ThreadMode.MainThread)
    public void userResult(NetResponse response){

        if (response.getRespType() == NetResponse.SUCCESS) {
            view.setOnActClick(1);
            ToastUtil.show("找回密码成功，请重新登录");
        }else {
            ToastUtil.show("找回密码失败，请稍后再试");
        }
    }

    private boolean isChickText() {

        if (DreamUtils.isEmpty(getUserPhone())) {
            ToastUtil.show(R.string.tv_input_phone);
            return false;
        }
        if (DreamUtils.isEmpty(getSmsCode())) {
            ToastUtil.show(R.string.tv_input_code);
            return false;
        }
        if (DreamUtils.isEmpty(getPsd())) {
            ToastUtil.show(R.string.tv_input_psd);
            return false;
        }
        return true;
    }
}
