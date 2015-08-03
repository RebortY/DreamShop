package com.dream.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    /**
     * 发送网络post 请求
     * @param url
     * @param params   参数列表
     */
    public  void netJsonPost(String url , HashMap<String , Object> params){
        String jstr = null;
        if (params == null) {
            jstr = JSON.toJSONString(params);
        }
        DreamRequest request = new DreamRequest(Request.Method.POST, url, jstr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void sendNetData(Request<?> request) {
        requestQueue.add(request);
        requestQueue.start();
    }

}
