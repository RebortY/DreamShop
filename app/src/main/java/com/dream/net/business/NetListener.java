package com.dream.net.business;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.main.DreamApplication;
import com.dream.net.ErrorValue;
import com.dream.net.NetFail;
import com.dream.net.NetSuccess;
import com.dream.util.EVENTTAG;
import com.github.snowdream.android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangll on 15/8/5.
 * 网络层处理事件回调
 * 网络产生成功，返回json数据，失败则抛出异常
 */
public class NetListener implements Response.Listener<JSONObject>, Response.ErrorListener {

    String TAG = EVENTTAG.NETTAG;

    public NetListener() {
    }

    public NetListener(String TAG) {
        if (TAG == null) {
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

    private void obtainSuccess(JSONObject Jobject) {
        try {
            String code = Jobject.getString("code");
            Log.v("recv ----> TAG = " + TAG + " ---" + Jobject.toString());
            if (code.equalsIgnoreCase(RespCode.SUCCESS)) {
                NetSuccess success = null;
                if (Jobject.isNull("data")) {
                    success = new NetSuccess(null);
                } else {
                    success = new NetSuccess(Jobject.getJSONObject("data"));
                }
                DreamApplication.getApp().eventBus().post(success, TAG);
            } else {
                String data = Jobject.getString("data");
                NetFail fail = new NetFail(new ErrorValue(code, data));
                DreamApplication.getApp().eventBus().post(fail, TAG);
            }
        } catch (JSONException e) {
            NetFail fail = new NetFail(ErrorValue.JSONFRAMATFAIL);
            DreamApplication.getApp().eventBus().post(fail, TAG);
        }
    }

    private void obtainFail(VolleyError error) {
        int respCode = -1;
        String str = null;
        if (error.networkResponse != null && error.networkResponse.data != null) {
            str = new String(error.networkResponse.data);
            respCode = error.networkResponse.statusCode;
        } else if (error.networkResponse != null) {
            respCode = error.networkResponse.statusCode;
        }
        Log.v("net err ----> TAG= "+ TAG+  " code =" + respCode + "\n" + str);
        String msg = error.getMessage();
        NetFail fail = new NetFail(new ErrorValue(String.valueOf(respCode), msg));
        DreamApplication.getApp().eventBus().post(fail, TAG);
    }

}
