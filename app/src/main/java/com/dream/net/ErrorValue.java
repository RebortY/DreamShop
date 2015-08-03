package com.dream.net;

/**
 * Created by yangll on 15/8/3.
 */
public enum ErrorValue {
    NETERROR(1,"网络异常") ,
    LOGINFAIL(10, "登录失败");

    int errorcode;
    String errorValue;
    ErrorValue(int code , String value) {
        errorcode = code;
        errorValue = value;
    }
}
