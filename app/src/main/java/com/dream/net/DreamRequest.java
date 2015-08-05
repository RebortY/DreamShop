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

    public DreamRequest(String url, NetListener listener) {
        super(url, listener, listener);
    }

    public DreamRequest(int method, String url, String requestBody, NetListener listener) {
        super(method, url, requestBody, listener, listener);
    }

    public void setCookie(String cookie){
        mHeaders.put("Cookie", cookie);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders;
    }
}
