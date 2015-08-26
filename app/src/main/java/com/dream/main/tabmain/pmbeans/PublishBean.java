package com.dream.main.tabmain.pmbeans;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/8/24.
 *
 * 最新揭晓 的构造类
 */
public class PublishBean extends AbstractBean {


    public PublishBean(Good goodItem) {
        super(goodItem);
    }

    public String getTitle() {
        return goodItem.getTitle();
    }

    public String getTime() {
        return goodItem.getTime();
    }

}
