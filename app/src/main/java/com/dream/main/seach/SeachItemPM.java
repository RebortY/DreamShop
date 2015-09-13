package com.dream.main.seach;

import com.dream.bean.SeachGood;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/9/9.
 */
public class SeachItemPM implements ItemPresentationModel<SeachGood> {

    private SeachGood good;

    @Override
    public void updateData(SeachGood good, ItemContext itemContext) {
        this.good = good;
    }

    //图片的url
    private String url = "file://drawable/R.drawable.ic_launcher";
    //商品钱数
    private String maney;
    //商品标题
    private String title;

    private int imax = 0;
    private int imin = 0;
    private int iprogress;

    public String getUrl() {
        return url = good.getThumb() == null ? "res://R.drawable.ic_launcher" : good.getThumb();
    }

    public String getManey() {
        return good.getMoney() + "";
    }

    public String getTitle() {
        return good.getTitle();
    }

    public String getTime() {
        return good.getCanyurenshu() +"/"+ good.getZongrenshu();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setManey(String maney) {
        this.maney = maney;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImax() {
        return good.getZongrenshu();
    }

    public void setImax(int imax) {
        this.imax = imax;
    }

    public int getImin() {
        return imin;
    }

    public void setImin(int imin) {
        this.imin = imin;
    }

    public int getIprogress() {
        return good.getCanyurenshu();
    }

    public void setIprogress(int iprogress) {
        this.iprogress = iprogress;
    }

}