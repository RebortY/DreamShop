package com.dream.bean;

import java.util.List;


/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/10/27 00:24
 * 支付前获取余额
 */
public class PayMoney {


    /**
     * uid : 1
     * money : 4
     * list : [{"id":195,"num":3},{"id":46,"num":1}]
     * userMoney : 3163
     */

    private int uid;
    private int money;
    private int userMoney;
    /**
     * id : 195
     * num : 3
     */

    private List<ListEntity> list;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getUid() {
        return uid;
    }

    public int getMoney() {
        return money;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private int id;
        private int num;

        public void setId(int id) {
            this.id = id;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getId() {
            return id;
        }

        public int getNum() {
            return num;
        }
    }
}
