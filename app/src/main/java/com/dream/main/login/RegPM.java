package com.dream.main.login;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.JsonBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.ErrorValue;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 21:56
 */
@PresentationModel
public class RegPM extends TitleBarPM {

    private final String TYPE_SMS_CODE = "TYPE_SMS_CODE"; //验证码
    private final String TYPE_REG = "TYPE_SMS_REG"; //注册

    Context mContext;

    RegView baseActView;

    String userPhone;

    String smsCode;

    String psd;

    String psdConfirm;


    public RegPM(Context context, RegView baseActViews) {
        this.baseActView = baseActViews;
        this.mContext = context;

        DreamApplication.getApp().eventBus().register(this);
    }


    public String getPsdConfirm() {
        return psdConfirm;
    }

    public void setPsdConfirm(String psdConfirm) {
        this.psdConfirm = psdConfirm;
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
            case R.id.bt_reg:
                if (isChickText()) {
                    userReg();
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
            DreamApplication.getApp().getDreamNet().netJsonPost(TYPE_SMS_CODE, ProtocolUrl.SMS_CODE, params);
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
            //存到本地缓存中
            try {
                JSONObject jsonObj = (JSONObject) response.getResp();
                JsonBean jsonBean = JSON.parseObject(response.getResp().toString(), JsonBean.class);
                Log.v("JSON ************---->" + jsonBean.getData());
                String jsonStr = null;
                jsonStr = jsonObj.getJSONArray("items").toString();
                Log.v("JSON ---->" + jsonStr);
            } catch (JSONException ex) {
                Log.v("JSON 格式化错误");
            }
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
        if (DreamUtils.isEmpty(getPsdConfirm())) {
            ToastUtil.show(R.string.tv_input_psd2);
            return false;
        }
        if (!getPsd().equals(getPsdConfirm())) {
            ToastUtil.show(R.string.tv_input_psd_dif);
            return false;
        }
        return true;
    }


    /**
     * 注册
     */
    private void userReg() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", getUserPhone());
        params.put("code", getSmsCode());
        params.put("password", getPsd());
        DreamApplication.getApp().getDreamNet().netJsonPost(TYPE_REG, ProtocolUrl.USER_REG, params);
    }

    /**
     * 注册结果处理
     */
    @Subcriber(tag = TYPE_REG, threadMode = ThreadMode.MainThread)
    public void userRegResult(NetResponse response){

        if (((ErrorValue) response.getResp()).getErrorcode() == "A00000") {

//            ToastUtil.show("注册成功，请登录");
            LoginHandler.getinstance().login(LoginHandler.LOGIN_PHONE, getUserPhone(), getPsd());
        } else {
            ToastUtil.show(((ErrorValue) response.getResp()).getErrorValue());
        }
    }

    /**
     * 注册成功后的登录返回
     *
     * @param resp
     */

    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {

        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
            baseActView.setOnActClick();
        } else {
            ToastUtil.show(R.string.tv_reg_error);
        }
    }

    @Override
    public String getTitleBar() {
        return mContext.getResources().getString(R.string.tv_register);
    }

}
