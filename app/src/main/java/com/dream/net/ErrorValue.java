package com.dream.net;

import org.apache.commons.lang.StringUtils;

/**
 * Created by yangll on 15/8/3.
 */
public class ErrorValue {

    public static ErrorValue NETERROR = new ErrorValue("mobile_1","网络异常");
    public static ErrorValue LOGINFAIL = new ErrorValue("mobile_1","登录失败");;
    public static ErrorValue JSONFRAMATFAIL = new ErrorValue("mobile_1","JSON格式化错误");

    private String errorcode;
    private String errorValue;
    private boolean isNetError = false;
    public ErrorValue(String code , String value) {
        if(StringUtils.isNumeric(code)){
            isNetError = true;
        }
        errorcode = code;
        errorValue = value;
    }

    public boolean isNetError() {
        return isNetError;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public String getErrorValue() {
        return errorValue;
    }
}
