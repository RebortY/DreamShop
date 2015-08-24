package com.dream.main.tabmain.pmbeans;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/8/24.
 * 热门活动的商品
 */
public class HotGoodBean extends AbstractBean{

    public HotGoodBean(Good goodItem) {
        super(goodItem);
    }

    //原价
    private String price;
    //优惠价
    private String offPrice;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
