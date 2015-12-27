package com.dream.main.goodpay;

import com.dream.bean.Good;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * @author yangll
 */
public class GoodPayItemPM implements ItemPresentationModel<Good> {

    Good good;

    private String goodtitle;
    private String count;
    private String money;
    private String money_shi;

    @Override
    public void updateData(Good good, ItemContext itemContext) {
        this.good = good;
        goodtitle = good.getTitle();
        count = good.getAddCount()+"";
//        float cm =  good.getAddCount() * Float.parseFloat(good.getMoney());
        money = count;
        money_shi = count;
    }

    public String getGoodtitle() {
        return goodtitle;
    }

    public String getCount() {
        return count;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney_shi() {
        return money_shi;
    }

    public void setMoney_shi(String money_shi) {
        this.money_shi = money_shi;
    }
}
