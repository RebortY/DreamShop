package com.dream.net;

/**
 * Created by yangll on 15/8/3.
 */
public interface NetResponse {
    public static final int SUCCESS = 0X011;
    public static final int FAIL = 0X012;
    public Object getResp();
    public int getRespType();
}
