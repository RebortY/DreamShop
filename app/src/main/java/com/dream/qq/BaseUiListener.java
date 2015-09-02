package com.dream.qq;

import android.content.Context;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/30 18:31
 * QQ登录回调
 */
public abstract class BaseUiListener implements IUiListener {

    int TYPE;//0登录   1获取用户信息
    protected BaseUiListener(int type){
        this.TYPE = type;
    }


    @Override
    public void onComplete(Object response) {
        doComplete(TYPE, (JSONObject)response);
    }

    @Override
    public void onError(UiError e) {
    }

    @Override
    public void onCancel() {
    }

    protected abstract void doComplete(int tag, JSONObject values);

}
