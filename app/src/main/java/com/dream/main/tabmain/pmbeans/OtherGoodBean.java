package com.dream.main.tabmain.pmbeans;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/8/24.
 */
public class OtherGoodBean extends AbstractBean{

    public OtherGoodBean(Good goodItem) {
        super(goodItem);
    }

    public String getMoney() {
        return goodItem.getMoney();
    }

    public void setMoney(String money) {
        goodItem.setMoney(money);
    }
}
