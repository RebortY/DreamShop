package com.dream.main.tabme.prize;

import com.dream.bean.MyPrizeInfo;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/15 19:42
 */
public class MyPrizeItemPM implements ItemPresentationModel<MyPrizeInfo> {

    MyPrizeInfo info;

    String name;

    String tvZxrc;

    String tvHdz;

    String tvDate;

    String url;

    private boolean circle = true;

    @Override
    public void updateData(MyPrizeInfo myPrizeInfo, ItemContext itemContext) {
        info = myPrizeInfo;
    }

    public MyPrizeInfo getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public String getTvZxrc() {
        return tvZxrc;
    }

    public String getTvHdz() {
        return tvHdz;
    }

    public String getTvDate() {
        return tvDate;
    }

    public String getUrl() {
        return info.getThumb() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }
}
