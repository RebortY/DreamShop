package com.dream.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.JsonBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.ErrorValue;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginHandler;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.StringUtils;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;


/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 20:19
 * 注册
 */

public class RegAct extends BaseActivity implements BaseActView {


    private final String TYPE_SMS_CODE = "TYPE_SMS_CODE"; //验证码
    private final String TYPE_REG = "TYPE_SMS_REG"; //注册

    RegPM regPM;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.bt_reg)
    Button btReg;

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public Object initPM() {
        regPM = new RegPM(this);
        return regPM;
    }

    @Override
    public void initView() {
        DreamApplication.getApp().eventBus().register(this);
    }

    @Override
    public void setOnClickView(View view) {

        switch (view.getId()) {
            case R.id.bt_get_code:
                getSmsCode();
                break;
            case R.id.bt_reg:
                if (isChickText()) {
                    userReg();
                }
                break;
        }
    }

    private boolean isChickText() {

        if (StringUtils.isEmpty(regPM.getUserPhone())) {
            ToastUtil.show(R.string.tv_input_phone);
            return false;
        }
        if (StringUtils.isEmpty(regPM.getSmsCode())) {
            ToastUtil.show(R.string.tv_input_code);
            return false;
        }
        if (StringUtils.isEmpty(regPM.getPsd())) {
            ToastUtil.show(R.string.tv_input_psd);
            return false;
        }
        if (StringUtils.isEmpty(regPM.getPsdConfirm())) {
            ToastUtil.show(R.string.tv_input_psd2);
            return false;
        }
        if (!regPM.getPsd().equals(regPM.getPsdConfirm())) {
            ToastUtil.show(R.string.tv_input_psd_dif);
            return false;
        }
        return true;
    }

    /**
     * get短信验证码
     */
    private void getSmsCode() {
        if (StringUtils.isEmpty(regPM.getUserPhone())) {
            ToastUtil.show(R.string.tv_input_phone);
        } else {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("phone", regPM.getUserPhone());
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
            JSONObject jsonObj = (JSONObject) response.getResp();
            JsonBean jsonBean = JSON.parseObject(response.getResp().toString(), JsonBean.class);
            Log.v("JSON ************---->" + jsonBean.getData());
            String jsonStr = null;
            try {
                jsonStr = jsonObj.getJSONArray("items").toString();
                Log.v("JSON ---->" + jsonStr);
            } catch (JSONException ex) {
                Log.v("JSON 格式化错误 ---->" + jsonStr);
            }
        }
    }

    /**
     * 注册
     */
    private void userReg() {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("phone", regPM.getUserPhone());
        params.put("code", regPM.getSmsCode());
        params.put("password", regPM.getPsd());
        DreamApplication.getApp().getDreamNet().netJsonPost(TYPE_REG, ProtocolUrl.USER_REG, params);
    }

    /**
     * 注册结果处理
     */
    @Subcriber(tag = TYPE_REG, threadMode = ThreadMode.MainThread)
    public void userRegResult(NetResponse response) throws JSONException {

//        JSONObject jsonObj = (JSONObject) response.getResp().toString();
//        JsonBean jsonBean = JSON.parseObject(jsonStr, JsonBean.class);

        ToastUtil.show("1");

        if (((ErrorValue) response.getResp()).getErrorcode() == "A00000") {
            startActivity(new Intent(this, LoginAct.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            ToastUtil.show("注册成功，请登录");
            LoginHandler.getinstance().login(LoginHandler.LOGIN_PHONE, LoginHandler.LOGINHANDLER, regPM.getUserPhone(), regPM.getPsd());
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
            finish();
        } else {
            ToastUtil.show(R.string.tv_reg_error);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
