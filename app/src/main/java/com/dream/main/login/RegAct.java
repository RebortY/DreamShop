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
import com.dream.util.DreamUtils;
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

public class RegAct extends BaseActivity implements RegView {


    RegPM regPM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    public Object initPM() {
        if(regPM == null){
            regPM = new RegPM(this, this);
        }
        return regPM;
    }

    @Override
    public void setOnClickView(View view) {


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
}
