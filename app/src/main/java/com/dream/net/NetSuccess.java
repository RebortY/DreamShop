package com.dream.net;


import org.json.JSONObject;

/**
 * Created by yangll on 15/8/3.
 */
public class NetSuccess implements NetResponse {
    //成功是服务端返回Json 数据结构
    JSONObject job = null;
    public NetSuccess(JSONObject object) {
        job = object;
    }

    @Override
    public Object getResp() {
        return job;
    }

    @Override
    public int getRespType() {
        return NetSuccess.SUCCESS;
    }
}
