package com.dream.qq;

import com.dream.main.MainActivity;
import com.dream.util.ToastUtil;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/30 18:31
 * QQ登录回调
 */
public class BaseUiListener implements IUiListener {

    @Override
    public void onComplete(Object response) {
        if (null == response) {
            ToastUtil.show("返回为空 登录失败");
            return;
        }
        JSONObject jsonResponse = (JSONObject) response;
        if (null != jsonResponse && jsonResponse.length() == 0) {
            ToastUtil.show("返回为空 登录失败");
            return;
        }
        ToastUtil.show("登录成功");
        doComplete((JSONObject)response);
    }

    protected void doComplete(JSONObject values) {

    }

    @Override
    public void onError(UiError e) {
        ToastUtil.show(e.errorDetail);
    }

    @Override
    public void onCancel() {

    }
}
