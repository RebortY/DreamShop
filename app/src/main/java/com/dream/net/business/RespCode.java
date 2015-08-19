package com.dream.net.business;

/**
 * Created by yangll on 15/8/18.
 */
public class RespCode {
     public static final String SUCCESS = "A00000"; // 成功
     public static final String AUTHFAIL= "A00001"; // 未登陆
     public static final String RESNOEXIST = "A00002"; // 资源不存在
     public static final String USEREXIST  = "A00003"; //用户已存在
     public static final String SERVERERROR ="B00003"; // 内部异常
     public static final String OPERERROR = "B00004" ;// 操作失败
     public static final String PARAMSERROR= "B00005"; // 参数错误
     public static final String NOAUTH =  "B00008"; // 没有权限
}
