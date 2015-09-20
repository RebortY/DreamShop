package com.dream.main.infoview;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.Good;
import com.dream.bean.goodinfo.GoodInfo;
import com.dream.main.DreamApplication;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.shopcart.ShopCart;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/9/13.
 * 商品详情PM
 */
@PresentationModel
public class GoodInfoPM extends TitleBarPM{

    //商品tiitle
    private String goodtitle = "ceshi";
    //商品Url
    private String url = "file://drawable/R.drawable.good_default";

    private final String GOODINFOTAG = "GOODINFO_TAG";

    private float aspectRatio = 1.5f;

    GoodInfoView view = null;
    MaterialPullRefreshEvent tempEvent = null;
    private String goodId;
    GoodInfo info = null;

    public GoodInfoPM(GoodInfoView view) {
        this.view = view;
        DreamApplication.getApp().eventBus().register(this);
        setTitleBar("商品详情");
    }

    //设置传入的 商品ID
    public void setGood(String id) {
        goodId = id;
    }

    @Subcriber(tag = GOODINFOTAG, threadMode = ThreadMode.MainThread)
    public void resphandle(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            JSONObject obj = (JSONObject) response.getResp();
            try {
                String str = (obj.getJSONObject("data")).getJSONObject("shop").toString();
                GoodInfo info = JSON.parseObject(str, GoodInfo.class);
                this.info = info;
                setGoodtitle(info.getTitle());
                setUrl(info.getThumb());
                view.setCanyuTextCount(info.getMe_gonumber());

                if(info.getZongrenshu() != info.getCanyurenshu()){
                    view.replaceForState(GoodInfoActivity.jinxingzhong);
                }else{
                    view.replaceForState(GoodInfoActivity.jiexiao);
                }

            } catch (JSONException E) {
                ToastUtil.show("商品详情解析失败");
            }
        } else {
            ToastUtil.show("获取详情失败");
        }
        if(tempEvent != null)
        view.stopRefresh(tempEvent.getView());
    }


    public String getUrl() {
        return url;
    }


    public String getGoodtitle() {
        return goodtitle;
    }

    public void setGoodtitle(String goodtitle) {
        this.goodtitle = goodtitle;
        pmRefresh("goodtitle");
    }

    public void setUrl(String url) {
        this.url = url == null ? this.url : url;
        pmRefresh("url");
    }

    //我参与的， 图文详情
    public void onClicks(ClickEvent clickEvent) {

        switch(clickEvent.getView().getId()){
            case R.id.shopcart_add: //加入购物车
                if(info != null){
                    Good good = new Good();
                    good.setId(info.getId()+"");
                    good.setSid(info.getSid()+"");
                    good.setThumb(info.getThumb());
                    good.setMoney(info.getMoney()+"");
                    good.setCanyurenshu(info.getCanyurenshu()+"");
                    good.setZongrenshu(info.getZongrenshu()+"");
                    good.setTitle(info.getTitle());
                    good.setTitle2(info.getTitle2());
                    boolean isok =  ShopCart.getShopCart().addShop(good);
                    ToastUtil.show( isok ? "以添加到购物车" : "添加失败，请先登录");
                }
                break;
        }

        view.onClick(clickEvent.getView());
    }

    public void unRegist() {
        DreamApplication.getApp().eventBus().unregister(this);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event)
    {
        tempEvent = event;
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", goodId);
        DreamApplication.getApp().getDreamNet().netJsonPost(GOODINFOTAG, ProtocolUrl.GOODINFO, params);
    }

    public GoodInfo getGoodInfo(){
        return info;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

}
