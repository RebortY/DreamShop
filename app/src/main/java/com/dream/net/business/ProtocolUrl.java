package com.dream.net.business;

/**
 * Created by yangll on 15/8/18.
 * 协议请求
 */
public class ProtocolUrl {

    private static final String ROOT = "http://api.1yuanmeng.com:8080/api";
    //登录 POST
    public static final String LOGIN = ROOT + "/user/login";
    //QQ登录 POST
    public static final String LOGIN_QQ = ROOT + "/user/qqlogin";
    //最新揭晓 GET
    public static final String PUBLISH = ROOT + "/shop/newestAnnounced";
    //晒单列表  POST
    public static final String POSTLIST = ROOT + "/shop/postList";
    //焦点图 GET
    public static final String FOCUS = ROOT + "/slides";
    //人气揭晓 , 总需人数， 即将揭晓  post
    public static final String SHOPLIST = ROOT + "/shop/list";
    //注册 验证码 POST
    public static final String SMS_CODE = ROOT + "/user/register/makcode";
    //注册
    public static final String USER_REG = ROOT + "/user/register";
    //获取收获地址
    public static final String ADDRESS_LIST = ROOT + "/dizhi/list";
    //添加收获地址
    public static final String ADDRESS_LIST_ADD = ROOT + "/dizhi/add";
    //更新收获地址
    public static final String ADDRESS_LIST_UPDATA = ROOT + "/dizhi/update";
    //设置默认收获地址
    public static final String ADDRESS_LIST_DEF = ROOT + "/dizhi/setDefault";
    //评论列表 POST
    public static final String COMMENTLIST = ROOT + "/shop/huifulist";
    //添加评论
    public static final String COMMENTADD = ROOT + "/shop/huifuadd";
    //分类列表
    public static final String CATEGORYS = ROOT + "/shop/categorys";
    //我的元梦购记录(进行中)
    public static final String SHOP_MYBAY = ROOT + "/shop/myBuy";
    //我的元梦购记录(已揭晓)
    public static final String SHOP_MYBAY_UN = ROOT + "/shop/myBuy";
    //搜索
    public static final String SEACH = ROOT + "/shop/search";
    //充值明细
    public static final String USER_RECHARGE = ROOT + "/shop/userRecharge";
    //消费明细
    public static final String USER_CONSUMPTION = ROOT + "/shop/userConsumption";
    //商品详情
    public static final String GOODINFO = ROOT+"/shop/detail";
    //退出登录
    public static final String LOGOUT = ROOT+"/user/logout";
    //获得的商品
    public static final String USER_ORDER = ROOT+"/shop/userOrder";
<<<<<<< Updated upstream

    //计算结果页
    public static final String countResult =  "http://api.1yuanmeng.com:8080/api/shop/calResult/{0,number,#}";
=======
    //上传头像
    public static final String USER_PHOTO = ROOT+"/imageupload/userphoto";
    //更新用户啊信息
    public static final String USER_EDIT_INFO = ROOT+"/user/editinfo";

>>>>>>> Stashed changes

}
