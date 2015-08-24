package com.dream.main.tabmain;

import com.dream.main.tabmain.pmbeans.AbstractBean;

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
    private String url;
    //商品钱数
    private String maney;
    //商品标题
    private String title;
    //商品时间， 此时间的 TextView 会根据不同状态改变视图
    private String time;

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
        return bean == null ? url : bean.getUrl() ;
    }

    public String getManey() {
        return bean == null ? maney : bean.getManey() ;
    }

    public String getTitle() {
        return bean == null ? title : bean.getTitle();
    }

    //TODO 此处需要进行类型判断 ，返回相应的界面显示的值
    public String getTime() {
        return bean == null ? time : bean.getTime();
    }

    public void setTime(String time) {
        this.time = time;
    }
}
