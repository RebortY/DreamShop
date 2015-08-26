package com.dream.net.business;

/**
 * Created by yangll on 15/8/18.
 * 协议请求
 */
public class ProtocolUrl {

    private static final String ROOT = "http://api.1yuanmeng.com:8080/api";
    //登录 POST
    public static final String LOGIN = ROOT + "/user/login";
    //最新揭晓 GET
    public static final String PUBLISH = ROOT + "/shop/newestAnnounced";
    //焦点图 GET
    public static final String FOCUS = ROOT + "/slides";
    //人气揭晓 , 总需人数， 即将揭晓  post
    public static final String SHOPLIST = ROOT + "/shop/list";


}
