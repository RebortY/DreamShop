package com.dream.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dream.main.DreamApplication;
import com.dream.net.business.NetListener;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/2.
 * 网络管理类
 */
public class DreamNet {

    private  final String DREAM_TAG = "NET_REQUEST_TAG";

    RequestQueue requestQueue = null;
    private String cookie;
    public DreamNet(Context ctx) {
        requestQueue = Volley.newRequestQueue(ctx);
        DreamApplication.getApp().eventBus().register(this);
    }

    /**
     * 发送Get请求
     * @param TAG 请求相应的回传标识
     *             默认 EVENTTAG.NETTAG
     *             使用默认值，TAG 可传入 空
     * @param url 请求的地址
     */
    public void netJsonGet(String TAG , String url) {
        RequestData rdata = new RequestData(Request.Method.GET,TAG , url ,null);
        sendNetData(rdata);
    }

    /**
     * 发送网络post 请求
     * @param TAG  请求相应的回传标识
     *             默认 EVENTTAG.NETTAG
     *             使用默认值，TAG 可传入 空
     * @param url  请求的地址
     * @param params   参数列表
     */
    public synchronized void netJsonPost(String TAG , String url , HashMap<String , Object> params){
        RequestData rdata = new RequestData(Request.Method.POST,TAG , url ,params);
        DreamApplication.getApp().eventBus().post(rdata, DREAM_TAG);
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

    @Subcriber(tag = DREAM_TAG , threadMode = ThreadMode.Async)
    public void sendNetData(RequestData request) {
        DreamRequest req = null;
        if(request.getMethod() == Request.Method.GET){
            req = new DreamRequest(request.getMethod(), request.getUrl(), null , new NetListener(request.getTAG()));
        }else{
            String jsonStr = JSON.toJSONString(request.getParams());
            req = new DreamRequest(request.getMethod(), request.getUrl(), jsonStr , new NetListener(request.getTAG()));
        }
        if(cookie != null){
            req.setCookie(cookie);
        }
        requestQueue.add(req);
    }

}
