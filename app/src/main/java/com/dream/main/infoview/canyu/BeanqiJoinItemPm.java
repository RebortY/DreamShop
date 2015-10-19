package com.dream.main.infoview.canyu;

import android.view.View;

import com.dream.bean.goodinfo.RecordsEntity;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/9/14.
 * 揭晓列表
 */
public class BeanqiJoinItemPm implements ItemPresentationModel<RecordsEntity> {

    RecordsEntity entity ;

    //揭晓事件
    private String jiexiao_time;
    //中奖用户头像
    private String url = "file://drawable/R.drawable.default_head";
    private boolean circle = true;
    //参与人员
    private String winner;
    //
    private String luckcode;
    //参与次数 和 事件
    private String canyuren;

    private int showjiexiaotime = View.GONE;

    @Override
    public void updateData(RecordsEntity qishulistEntity, ItemContext itemContext) {
            entity = qishulistEntity;
    }

    public int getShowjiexiaotime() {
        return showjiexiaotime;
    }

    public String getJiexiao_time() {
        return jiexiao_time;
    }

    public String getUrl() {
        return entity.getUphoto() == null ? url : entity.getUphoto();
    }

    public boolean isCircle() {
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    public String getWinner() {
        return entity.getUsername();
    }

    public String getLuckcode() {
        return entity.getIp();
    }

    public void setLuckcode(String luckcode) {
        this.luckcode = luckcode;
    }

    public String getCanyuren() {
        return "参与期数："+entity.getShopqishu()+"期";
    }

}
