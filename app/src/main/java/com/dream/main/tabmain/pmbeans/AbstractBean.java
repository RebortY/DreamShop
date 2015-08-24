package com.dream.main.tabmain.pmbeans;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/8/24.
 */
public  abstract class AbstractBean {

    Good goodItem;
    public AbstractBean(Good goodItem) {
        this.goodItem = goodItem;
    }

    protected String url;
    protected String title;
    protected String maney;
    protected String time;

    public String getUrl() {
        return goodItem.getThumb();
    }

    public String getTitle() {
        return goodItem.getTitle();
    }

    public String getManey() {
        return goodItem.getMoney();
    }

    public String getTime() {
        return goodItem.getTime();
    }
}
