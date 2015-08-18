package com.dream.net;

import com.android.volley.VolleyError;

/**
 * Created by yangll on 15/8/3.
 */
public class NetFail implements NetResponse {

    ErrorValue vfalue ;

    public NetFail(ErrorValue error) {
        vfalue = error;
    }

    @Override
    public Object getResp() {
        return vfalue;
    }

    @Override
    public int getRespType() {
        return NetResponse.FAIL;
    }
}
