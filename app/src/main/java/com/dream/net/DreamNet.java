package com.dream.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.dream.net.business.NetListener;

import java.util.HashMap;

/**
 * Created by yangll on 15/8/2.
 * 网络管理类
 */
public class DreamNet {

    RequestQueue requestQueue = null;
    private String cookie;
    public DreamNet(Context ctx) {
        requestQueue = Volley.newRequestQueue(ctx);
    }

    /**
     * 发送Get请求
     * @param TAG 请求相应的回传标识
     *             默认 EVENTTAG.NETTAG
     *             使用默认值，TAG 可传入 空
     * @param url 请求的地址
     */
    public void netJsonGet(String TAG , String url) {
        DreamRequest  dreamRequest = new DreamRequest(url, new NetListener(TAG));
        sendNetData(dreamRequest);
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
        String jstr = null;
        if (params == null) {
            jstr = JSON.toJSONString(params);
        }
        DreamRequest request = new DreamRequest(Request.Method.POST, url, jstr, new NetListener(TAG));
        sendNetData(request);
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

    private void sendNetData(DreamRequest request) {
        if(cookie != null){
            request.setCookie(cookie);
        }
        requestQueue.add(request);
        requestQueue.start();
    }

}
