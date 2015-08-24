package com.dream.main.tabmain.pmbeans;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/8/24.
 *
 * 最新揭晓 的构造类
 */
public class PublishBean extends AbstractBean {

    private String title;
    private String time;

    public PublishBean(Good goodItem) {
        super(goodItem);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
