package com.dream.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangll on 15/8/2.
 */
public class DreamRequest extends JsonObjectRequest {
    private Map<String, String> mHeaders=new HashMap<String, String>(1);

    public DreamRequest(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public DreamRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public DreamRequest(int method, String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public DreamRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    public DreamRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }


    public void setCookie(String cookie){
        mHeaders.put("Cookie", cookie);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }
}
