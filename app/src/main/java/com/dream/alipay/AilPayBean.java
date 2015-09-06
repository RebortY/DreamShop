package com.dream.alipay;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/6 23:52
 */
public class AilPayBean {

    String orderNum;//订单号

    String subject;//商品名称

    String body;//商品详情

    String price;//商品金额

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
