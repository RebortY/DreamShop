package com.dream.main.tabme.prize;

import com.dream.bean.MyPrizeInfo;
import com.dream.util.DreamUtils;
import com.github.snowdream.android.util.Log;

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

    String name;//商品名称

    String tvZxrc;//总需人次

    String tvHdz;//参与人次

    String tvDate;

    String url;

    String tvSpfc;//商品发出

    String tvQrsh;//确认收获

    private boolean circle = true;

    MyPrizeView myPrizeView;
    MyPrizeItemPM(MyPrizeView myPrizeViews){
        this.myPrizeView = myPrizeViews;
    }

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
        return "参与了" + String.valueOf(info.getCanyurenshu())+"次";
    }

    public String getTvDate() {
        return "揭晓时间：" + DreamUtils.formatSecTime(info.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public String getUrl() {
        return info.getThumb() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public String getTvSpfc() {
        return tvSpfc;
    }

    public String getTvQrsh() {
        return tvQrsh;
    }

    public void onClicks(ClickEvent event){
        myPrizeView.onClick(event.getView(), info);
    }
}
