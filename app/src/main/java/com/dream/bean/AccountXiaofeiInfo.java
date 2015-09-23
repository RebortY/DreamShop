package com.dream.bean;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/23 00:09
 */
public class AccountXiaofeiInfo {

    /**
     * content : 微信支付充值
     * uid : 279
     * time : 1436019892
     * money : 100
     * pay : 账户
     * type : 1
     */

    private String content;
    private int uid;
    private String time;
    private int money;
    private String pay;
    private int type;

    public void setContent(String content) {
        this.content = content;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getUid() {
        return uid;
    }

    public String getTime() {
        return time;
    }

    public int getMoney() {
        return money;
    }

    public String getPay() {
        return pay;
    }

    public int getType() {
        return type;
    }
}
