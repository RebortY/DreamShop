package com.dream.main.tabmain;

import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/8/24.
 *
 * 首页商品的 适配器处理类
 */
public class GoodsItemPM implements ItemPresentationModel<AbstractBean> {

    private AbstractBean bean;
    //图片的url
    private String url = "file://drawable/R.drawable.ic_launcher";
    //商品钱数
    private String maney;
    //商品标题
    private String title;

    private int imax = 0;
    private int imin = 0;
    private int iprogress;


    TabMainView view;

    public GoodsItemPM(TabMainView view) {
        this.view = view;
    }

    @Override
    public void updateData(AbstractBean goodBean, ItemContext itemContext) {
            bean = goodBean;
            view.handlGoodsView(goodBean , itemContext.getItemView());
    }

    public String getUrl() {
        return bean.getUrl() == null ? "file://drawable/R.drawable.ic_launcher" : bean.getUrl() ;
    }

    public String getManey() {
        return bean.getManey() ;
    }

    public String getTitle() {
        return bean.getTitle();
    }

    //TODO 此处需要进行类型判断 ，返回相应的界面显示的值
    public String getTime() {
        return DreamUtils.formatSecTime( Long.parseLong(bean.getTime()) , "yyyy-MM-dd") ;
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
         return Integer.parseInt(bean.getGood().getZongrenshu());
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
        return Integer.parseInt(bean.getGood().getCanyurenshu());
    }

    public void setIprogress(int iprogress) {
        this.iprogress = iprogress;
    }


}
