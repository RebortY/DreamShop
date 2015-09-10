package com.dream.main.tabme.record;

import com.dream.bean.MyDreamRecordInfo;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/10 21:13
 */
public class MyDreamRecordingFragmentItemPM implements ItemPresentationModel<MyDreamRecordInfo> {

    MyDreamRecordInfo info;

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
    public void updateData(MyDreamRecordInfo myDreamRecordInfo, ItemContext itemContext) {
        info = myDreamRecordInfo;
    }

    public String getUrl() {
        return info.getUphoto() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public boolean isCircle() {
        return circle;
    }
}
