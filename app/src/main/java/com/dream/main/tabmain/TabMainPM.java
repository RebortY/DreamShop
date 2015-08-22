package com.dream.main.tabmain;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/15.
 */
@PresentationModel
public class TabMainPM extends AbstractPM{

    private String url = "http://m.1yuanmeng.com/statics/uploads/banner/20150730/81318274218875.png";
    private String title = "首页";
    private boolean circle = false;

    public TabMainPM(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCircle() {
        return circle;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
