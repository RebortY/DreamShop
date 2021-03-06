package com.dream.main.tabmain;

import android.view.View;

import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.shopcart.ShopCart;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.view.ClickEvent;

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

    private int visibilityShopCart = View.VISIBLE;

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

    public int getVisibilityShopCart() {
        return Integer.parseInt(bean.getGood().getShenyurenshu()) != 0 ? View.VISIBLE : View.GONE;
    }

    public String getManey() {
        return "价值:"+ bean.getManey() ;
    }

    public String getTitle() {
        return bean.getTitle();
    }

    //TODO 此处需要进行类型判断 ，返回相应的界面显示的值
    public String getTime() {
        return DreamUtils.formatSecTime(Long.parseLong(bean.getTime()), "yyyy-MM-dd") ;
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

    public void addShopCart(ClickEvent event){
        boolean isadd = ShopCart.getShopCart().addShop(bean.getGood());
        if(isadd){
            ToastUtil.show("已加入购物车");
        }else{
            ToastUtil.show("您还没有登录哦");
        }
    }
}
