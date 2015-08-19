package com.dream.net;

import com.android.volley.Request;

import java.util.HashMap;

/**
 * Created by yangll on 15/8/19.
 */
public class RequestData {

    private int method;
    private String TAG;
    private String url;
    private Object params;

    public RequestData(int method ,String TAG, String url, Object params) {
        this.method = method;
        this.TAG = TAG;
        this.url = url;
        this.params = params;
    }

    public int getMethod() {
        return method;
    }

    public String getTAG() {
        return TAG;
    }

    public String getUrl() {
        return url;
    }

    public Object getParams() {
        return params;
    }
}
