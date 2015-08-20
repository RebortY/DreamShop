package com.dream.net.business.login;

import com.dream.bean.AuthUser;

/**
 * Created by yangll on 15/8/19.
 * 登录成功后返回的处理类
 *
 * 如果登录失败 user 为Null
 */
public class LoginResp {

    private String errorCode;
    private String errorMsg;
    private AuthUser user;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }
}
