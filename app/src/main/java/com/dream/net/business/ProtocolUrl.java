package com.dream.net.business;

/**
 * Created by yangll on 15/8/18.
 * 协议请求
 */
public class ProtocolUrl {

    private static final String ROOT = "http://api.1yuanmeng.com:8080/api";
    //登录 POST
    public static final String LOGIN = ROOT + "/user/login";
    //揭晓 GET
    public static final String PUBLISH = ROOT + "/shop/newestAnnounced";
    //焦点图 GET
    public static final String FOCUS = ROOT + "/slides";

}
