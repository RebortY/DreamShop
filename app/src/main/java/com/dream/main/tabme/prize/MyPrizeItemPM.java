package com.dream.main.tabme.prize;

import com.dream.bean.MyPrizeInfo;
import com.dream.util.DreamUtils;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/15 19:42
 */
@PresentationModel
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
        return info.getTitle();
    }

    public String getTvZxrc() {
        return "总需人次：" + String.valueOf(info.getZongrenshu()) + "人次";
    }

    public String getTvHdz() {
        return "您参与了" + String.valueOf(info.getCanyurenshu())+"人次";
    }

    public String getTvDate() {
        return "揭晓时间：" + DreamUtils.formatSecTime(info.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getUrl() {
        return info.getThumb() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public void onClicks(ClickEvent event){

    }
}
