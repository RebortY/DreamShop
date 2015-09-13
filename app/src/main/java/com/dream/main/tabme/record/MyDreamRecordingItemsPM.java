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

    private boolean circle = true;

    String name;

    String join;

    String joinAll;

    String joinSy;

    public String getName() {
        return info.getShopname();
    }

    public String getJoin() {
        return String.valueOf(info.getCanyurenshu());
    }

    public String getJoinAll() {
        return String.valueOf(info.getZongrenshu());
    }

    public String getJoinSy() {
        return String.valueOf(info.getShenyurenshu());
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
}
