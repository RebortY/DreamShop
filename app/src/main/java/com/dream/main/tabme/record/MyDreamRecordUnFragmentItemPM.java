package com.dream.main.tabme.record;

import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/11 00:42
 * 已揭晓
 */
public class MyDreamRecordUnFragmentItemPM implements ItemPresentationModel<MyDreamRecordUnInfo> {

    MyDreamRecordUnInfo info;

    private boolean circle = true;

    String name;//名称

    String tvZxrc;//总需人次

    String tvHdz;//获得者

    String tvDate;//揭晓时间


    @Override
    public void updateData(MyDreamRecordUnInfo myDreamRecordUnInfo, ItemContext itemContext) {
        info = myDreamRecordUnInfo;
    }

    public String getUrl() {
        return info.getUphoto() == null ? "res://R.drawable.ic_launcher" : info.getThumb();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getTvDate() {
        return info.getQ_end_time();
    }

    public String getName() {
        return info.getShopname();
    }

    public String getTvZxrc() {
        return String.valueOf(info.getZongrenshu());
    }

    public String getTvHdz() {
        return info.getHuode();
    }

    public void onClicks(ClickEvent event) {
    }
}
