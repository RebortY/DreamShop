package com.dream.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.dream.main.DreamApplication;
import com.dream.util.EVENTTAG;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by yangll on 15/8/2.
 * 网络管理类
 */
public class DreamNet {

    RequestQueue requestQueue = null;

    public DreamNet(Context ctx) {
        requestQueue = Volley.newRequestQueue(ctx);
    }

    /**
     * 发送Get请求
     * @param url
     */
    public void netJsonGet(String url) {
        DreamRequest  dreamRequest = new DreamRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                obtainSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                obtainFail(error);
            }
        });
        sendNetData(dreamRequest);
    }

    /**
     * 发送网络post 请求
     * @param url
     * @param params   参数列表
     */
    public synchronized void netJsonPost(String url , HashMap<String , Object> params){
        String jstr = null;
        if (params == null) {
            jstr = JSON.toJSONString(params);
        }
        DreamRequest request = new DreamRequest(Request.Method.POST, url, jstr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                obtainSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                obtainFail(error);
            }
        });
        sendNetData(request);
    }

    private void obtainSuccess(JSONObject Jobject){
        NetSuccess success  = new NetSuccess(Jobject);
        DreamApplication.getApp().eventBus().post(success , EVENTTAG.NETTAG);
    }

    private void obtainFail(VolleyError error){
        NetFail fail = new NetFail(error);
        DreamApplication.getApp().eventBus().post(fail , EVENTTAG.NETTAG);
    }

    private void sendNetData(Request<?> request) {
        requestQueue.add(request);
        requestQueue.start();
    }

}
