package com.dream.main.tabmain;

import com.alibaba.fastjson.JSON;
import com.dream.main.DreamApplication;
import com.dream.main.tabmain.pmbeans.PublishBean;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.views.AbstractPM;
import com.litesuits.android.log.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/15.
 */
@PresentationModel
public class TabMainPM extends AbstractPM {

    //轮播图
    private final String TAGSLIB = "TAG_SLIBS";

//    private String url = "http://m.1yuanmeng.com/statics/uploads/banner/20150730/81318274218875.png";
//    private boolean circle = false;

    private TabMainView view = null;

    //揭晓的数据集合
    private List<PublishBean> publishBeans = new ArrayList<PublishBean>();

    public TabMainPM(TabMainView view) {
        this.view = view;

        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(TAGSLIB, ProtocolUrl.FOCUS);
    }

    @Subcriber(tag = TAGSLIB, threadMode = ThreadMode.MainThread)
    public void handleSlibs(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            JSONObject jsonObj = (JSONObject) response.getResp();
            String jsonStr = null;
            try {
                jsonStr = jsonObj.getJSONArray("items").toString();
                DreamApplication.getApp().getSharedPreferences().add(TAGSLIB, jsonStr);
                List<Carousel> carousel = JSON.parseArray(jsonStr, Carousel.class);
                view.setCarouselAdapter(carousel);
            } catch (JSONException ex) {
                Log.v("JSON 格式化错误 ---->" + jsonStr);
            }
        }
    }


    /**
     * 点击 轮播图下面的 三个 操作按钮
     *
     * @param clickEvent
     */
    public void operClick(ClickEvent clickEvent) {
        switch (clickEvent.getView().getId()) {

        }
    }


    @ItemPresentationModel(value = GoodsItemPM.class, factoryMethod = "createItemPM")
    public List<PublishBean> getPublishBeans() {
        return publishBeans;
    }

    //商品构造器
    public GoodsItemPM createItemPM() {
        return new GoodsItemPM(view);
    }

    /**
     * 更新 最新揭晓的商品
     * @param publishBeans
     */
    public void setPublishBeans(List<PublishBean> publishBeans) {
        if (publishBeans == null) return;
        publishBeans.clear();
        publishBeans.addAll(publishBeans);
        getPresentationModelChangeSupport().firePropertyChange("publishBeans");
    }

    /**
     * 点击产品的事件
     * @param event
     */
    public void goodsItemClick(ItemClickEvent event) {


    }

    public void unregister() {
        DreamApplication.getApp().eventBus().unregister(this);
    }

}
