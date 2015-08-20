package com.dream.net.business.login;

import com.alibaba.fastjson.JSON;
import com.dream.bean.AuthUser;
import com.dream.db.table.Login;
import com.dream.main.DreamApplication;
import com.dream.net.ErrorValue;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.RespCode;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/18.
 * 登录处理类
 *
 * 需要登录的地方可以 调用此类， 登录，和 token失效重新登录，网络处理层实现自动重连工作
 *
 * LoginHandler 对外发出的 TAG 是  LoginTag.LOGIN  , 接收对象为 LoginResp 对象
 */
public class LoginHandler {

    private final String LOGINHANDLER = "TAG_LOGINHANDLER";
    private static LoginHandler loginInstance = null;

    private String phone ;
    private String pw;

    private LoginHandler() {
        DreamApplication.getApp().eventBus().register(this);
    }

    public static LoginHandler getinstance(){
        return loginInstance = loginInstance != null ? loginInstance : new LoginHandler();
    }

    /**
     * 登录
     * 请求协议 {"phone":"13401165595","password":"qwertyui"}
     * @param phone 登录名
     * @param password  密码
     */
    public void login(String phone , String password){
        //TODO 目前先做手机号登录，QQ登录后面在做
        this.phone = phone;
        this.pw = password;

        HashMap<String,Object> params = new HashMap<String ,Object>();
        params.put("phone",this.phone);
        params.put("password", this.pw); //TODO 目前登录应该没有做 sha加密，后续要增加

        //发送请求后，会通过 TAG 返回相应的结果
        DreamApplication.getApp().getDreamNet().netJsonPost( LOGINHANDLER, ProtocolUrl.LOGIN, params);
    }

    /**
     * 处理登录网络请求返回类
     * @param response
     */
    @Subcriber(tag = LOGINHANDLER , threadMode = ThreadMode.Async)
    public void loginResp(NetResponse response){
        LoginResp loginResp = getLoginResp(response);
        //对外发送登录信息
        postLoginResp(loginResp);
    }

    private LoginResp getLoginResp(NetResponse response) {
        //登录成功 获取token ,设置网络cookie  保存登录信息
        LoginResp loginResp = new LoginResp();
        if(response.getRespType() == NetResponse.SUCCESS){
            String jstr = response.getResp().toString();
            //解析登录对象
            Login loginBean =  JSON.parseObject(jstr, Login.class);
            //保存用户数据到数据库中
            loginBean.getUser().setPassword(pw);
            DreamApplication.getApp().getdb().save(loginBean.getUser());
            DreamApplication.getApp().getDreamNet().setCookie(loginBean.getToken());
            loginResp.setUser(loginBean.getUser());
        }else{
            //失败，判断是登录失败，还是 cookie 过期，如果过期 需要重新连接
            ErrorValue fail  = (ErrorValue) response.getResp();
            loginResp.setErrorCode(fail.getErrorcode());
            loginResp.setErrorMsg(fail.getErrorValue());
            if(fail.isNetError()){ //网络层异常，直接处理提示
                DreamApplication.getApp().eventBus().post(loginResp, LoginTag.LOGIN);
            }else if( fail.getErrorcode().equalsIgnoreCase(RespCode.AUTHFAIL)){
                // 不重新调用 登录， 界面给出提示，长时间没有登录，请重新登录
                DreamApplication.getApp().getUser().setIsLogin(false);
                AuthUser user = getLastLoginUser();
                login(user.getMobile(), user.getPassword());
            }
        }
        return loginResp;
    }

    //获取最后一次登录的用户
    public  AuthUser getLastLoginUser() {
        List<AuthUser> users = DreamApplication.getApp().getdb().query(QueryBuilder.create(AuthUser.class).appendOrderDescBy(AuthUser.COL_LASTTIME));
        if (users == null || users.size() == 0)
            return null;
        AuthUser user = users.get(0);
        return user;
    }

    private void postLoginResp(LoginResp loginResp){
        DreamApplication.getApp().setAuthUser(loginResp.getUser());
        DreamApplication.getApp().eventBus().post(loginResp, LoginTag.LOGIN);
        phone = null;
        pw = null;
    }

}
