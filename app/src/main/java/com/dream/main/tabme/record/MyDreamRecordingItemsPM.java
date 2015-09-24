package com.dream.main.tabme.record;

import com.dream.bean.MyDreamRecordingInfo;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/10 21:13
 * 正在进行中
 */
public class MyDreamRecordingItemsPM implements ItemPresentationModel<MyDreamRecordingInfo> {

    MyDreamRecordingInfo info;

    private boolean circle = false;

    String name;

    String join;

    String joinAll;

    String joinSy;

    int imax;

    int imin;

    int iprogress;

    boolean show;


    public String getName() {
        return info.getShopname();
    }

    public String getJoin() {
        return String.valueOf(info.getCanyurenshu() + "\n已参与");
    }

    public String getJoinAll() {
        return String.valueOf(info.getZongrenshu()) + "\n总需";
    }

    public String getJoinSy() {
        return String.valueOf(info.getShenyurenshu() + "\n剩余");
    }

    @Override
    public void updateData(MyDreamRecordingInfo myDreamRecordInfo, ItemContext itemContext) {
        info = myDreamRecordInfo;
    }

    public String getUrl() {
        return info.getUphoto() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public boolean isCircle() {
        return circle;
    }

    public int getImax() {
        return info.getZongrenshu();
    }

    public int getImin() {
        return imin;
    }

    public int getIprogress() {
        return info.getCanyurenshu();
    }

    public boolean isShow() {
        return false;
    }
}
