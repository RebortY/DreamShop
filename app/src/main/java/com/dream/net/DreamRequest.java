package com.dream.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dream.net.business.NetListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
        mHeaders.put("Cookie", "token="+cookie);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        mHeaders.put("Content-Type","application/json;charset=UTF-8");
        return mHeaders;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Response<JSONObject> jsonResp =  Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
            return jsonResp;
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }




}
