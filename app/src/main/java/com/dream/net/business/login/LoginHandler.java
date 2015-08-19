package com.dream.net.business.login;

import com.dream.main.DreamApplication;
import com.dream.net.ErrorValue;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.RespCode;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/18.
 * 登录处理类
 *
 * 需要登录的地方可以 调用此类， 登录，和 token失效重新登录，网络处理层实现自动重连工作
 *
 * LoginHandler 对外发出的 TAG 是  LoginTag.LOGIN
 */
public class LoginHandler {

    private final String LOGINHANDLER = "TAG_LOGINHANDLER";
    private static LoginHandler loginInstance = null;

    private LoginHandler() {
        DreamApplication.getApp().eventBus().register(this);
    }

    public static LoginHandler getinstance(){
        return loginInstance = loginInstance == null ? loginInstance : new LoginHandler();
    }

    /**
     * 登录
     * 请求协议 {"phone":"13401165595","password":"qwertyui"}
     * @param phone 登录名
     * @param password  密码
     */
    public void login(String phone , String password){
        //TODO 目前先做手机号登录，QQ登录后面在做
        HashMap<String,Object> params = new HashMap<String ,Object>();
        params.put("phone",phone);
        params.put("password",password); //TODO 目前登录应该没有做 sha加密，后续要增加
        //发送请求后，会通过 TAG 返回相应的结果
        DreamApplication.getApp().getDreamNet().netJsonPost(LOGINHANDLER, ProtocolUrl.LOGIN, params);
    }

    /**
     * 处理登录网络请求返回类
     * @param response
     */
    @Subcriber(tag = LOGINHANDLER , threadMode = ThreadMode.Async)
    private void loginResp(NetResponse response){
        //登录成功 获取token ,设置网络cookie  保存登录信息
        if(response.getRespType() == NetResponse.SUCCESS){
            //TODO 根据返回协议中 data 的数据，用fastJson进行解析,保存登录数据
            //保存用户数据到数据库中
            DreamApplication.getApp().getDreamNet().setCookie("");
            DreamApplication.getApp().eventBus().post("",LoginTag.LOGIN);
        }else{
            //失败，判断是登录失败，还是 cookie 过期，如果过期 需要重新连接
            ErrorValue fail  = (ErrorValue) response.getResp();
            if(fail.isNetError()){ //网络层异常，直接处理提示
                //TODO 对外发送登录错误时 网络的错误 ， 网络异常错误提醒，可以统一处理
                //发送登录信息
                DreamApplication.getApp().eventBus().post("",LoginTag.LOGIN);
            }else if( fail.getErrorcode().equalsIgnoreCase(RespCode.AUTHFAIL)){
               //认证失败，重新调用一下登录
            }else{
               //发送给前端错误
                DreamApplication.getApp().eventBus().post("",LoginTag.LOGIN);
            }
        }
    }

}
