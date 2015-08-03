package com.dream.net;

import com.android.volley.VolleyError;

/**
 * Created by yangll on 15/8/3.
 */
public class NetFail implements NetResponse {

    ErrorValue vfalue ;

    public NetFail(VolleyError error) {
        vfalue = ErrorValue.LOGINFAIL;
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
