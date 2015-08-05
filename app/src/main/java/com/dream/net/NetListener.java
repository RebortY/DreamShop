package com.dream.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.main.DreamApplication;
import com.dream.util.EVENTTAG;

import org.json.JSONObject;

/**
 * Created by yangll on 15/8/5.
 * 网络层处理事件回调
 * 网络产生成功，返回json数据，失败则抛出异常
 */
public class NetListener implements Response.Listener<JSONObject> , Response.ErrorListener {

    String TAG = EVENTTAG.NETTAG;
    public NetListener() {
    }

    public NetListener(String TAG) {
        if(TAG == null){
            this.TAG = EVENTTAG.NETTAG;
        }
        this.TAG = TAG;
    }

    @Override
    public void onResponse(JSONObject response) {
        obtainSuccess(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        obtainFail(error);
    }

    private void obtainSuccess(JSONObject Jobject){
        NetSuccess success  = new NetSuccess(Jobject);
        DreamApplication.getApp().eventBus().post(success , TAG);
    }

    private void obtainFail(VolleyError error){
        NetFail fail = new NetFail(error);
        DreamApplication.getApp().eventBus().post(fail , TAG);
    }

}
