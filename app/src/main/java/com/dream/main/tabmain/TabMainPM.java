package com.dream.main.tabmain;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.tabmain.pmbeans.OtherGoodBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/15.
 */
@PresentationModel
public class TabMainPM extends AbstractPM {

    //轮播图
    private final String TAGSLIB_FOCUS = "TAG_SLIBS";
    //最新揭晓
    private final String TAGSLIB_LAST_PUBLISH = "TAG_LAST_PUBLISH";

    // 人气，价格，即将揭晓，最新  排序
    private final String TYPE_RQ = "TYPE_RQ"; //人气
    private final String TYPE_JX = "TYPE_JX"; //即将揭晓
    private final String TYPE_NEW= "TYPE_NEW"; //最新
    private final String TYPE_JG = "TYPE_JG"; //价格

    private int currType = R.id.type_jx;

    private TabMainView view = null;



    //揭晓的数据集合
    private List<PublishBean> publishBeans = new ArrayList<PublishBean>();
    // 人气， 即将 ，最新揭晓 ，价格 所需要的列表
    private List<OtherGoodBean> goods = new ArrayList<>();

    public TabMainPM(TabMainView view) {
        this.view = view;

        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(TAGSLIB_FOCUS, ProtocolUrl.FOCUS);
        DreamApplication.getApp().getDreamNet().netJsonGet(TAGSLIB_LAST_PUBLISH , ProtocolUrl.PUBLISH);
        getGoodsByType(currType , 1);
    }

    //轮播图
    @Subcriber(tag = TAGSLIB_FOCUS, threadMode = ThreadMode.MainThread)
    public void handleSlibs(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            JSONObject jsonObj = (JSONObject) response.getResp();
            String jsonStr = null;
            try {
                jsonStr = jsonObj.getJSONArray("items").toString();
                DreamApplication.getApp().getSharedPreferences().add(TAGSLIB_FOCUS, jsonStr);
                List<Carousel> carousel = JSON.parseArray(jsonStr, Carousel.class);
                view.setCarouselAdapter(carousel);
            } catch (JSONException ex) {
                Log.v("JSON 格式化错误 ---->" + jsonStr);
            }
        }
    }

    //最新揭晓
    @Subcriber(tag = TAGSLIB_LAST_PUBLISH, threadMode = ThreadMode.MainThread)
    public void handleSlibPulish(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response , TAGSLIB_LAST_PUBLISH);
        }else{
            view.showToast("网络异常，请检查网络");
        }
    }

    //最新人气
    @Subcriber(tag = TYPE_RQ, threadMode = ThreadMode.MainThread)
    public void type_rq(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response , TYPE_RQ);
        }else{
            view.showToast("网络异常，请检查网络");
        }
    }

    //即将揭晓
    @Subcriber(tag = TYPE_JX, threadMode = ThreadMode.MainThread)
    public void type_jx(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response , TYPE_JX);
        }else{
            view.showToast("网络异常，请检查网络");
        }
    }

    //最新
    @Subcriber(tag = TYPE_NEW, threadMode = ThreadMode.MainThread)
    public void type_new(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response , TYPE_NEW);
        }else{
            view.showToast("网络异常，请检查网络");
        }
    }

    //价格
    @Subcriber(tag = TYPE_JG, threadMode = ThreadMode.MainThread)
    public void type_jg(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response , TYPE_JG);
        }else{
            view.showToast("网络异常，请检查网络");
        }
    }


    //处理返回商品
    private void respGoods(NetResponse response , String tag) {
        JSONObject jsonObj = (JSONObject) response.getResp();
        String jsonStr = null;
        try {
            jsonStr = jsonObj.getJSONArray("list").toString();
            DreamApplication.getApp().getSharedPreferences().add(tag, jsonStr);
            List<Good> jgoods = JSON.parseArray(jsonStr, Good.class);
            if(tag.equals(TAGSLIB_LAST_PUBLISH)){
                publishBeans.clear();
                int index = 0;
                for(Good g : jgoods){
                    PublishBean pb = new PublishBean(g);
                    if(index > 2) break;
                    publishBeans.add(pb);
                    index++;
                }
                getPresentationModelChangeSupport().firePropertyChange("publishBeans");
            }else {
                goods.clear();
                for(Good g : jgoods){
                    OtherGoodBean ogb = new OtherGoodBean(g);
                    goods.add(ogb);
                }
                getPresentationModelChangeSupport().firePropertyChange("goods");
            }
        } catch (JSONException ex) {
            Log.v("JSON 格式化错误 ---->" + jsonStr);
        }
    }

    public void clickByType(ClickEvent event){
        int id =  event.getView().getId();
        getGoodsByType(id ,  1);
    }
    /**
     * 通过选择类型
     * @param id  翻页类型对应的view id
     * @param page 翻页参数
     */
    public void getGoodsByType(int id , int page){
        HashMap<String ,Object> params = new HashMap<String , Object>();
        String tag = TYPE_JX;
        int type = 10; //即将揭晓的类型
        switch (id){
            case R.id.type_jg://价格
                tag = TYPE_JG;
                type = 50;
                break;
            case R.id.type_new://最新  //TODO 最新可能需要单独处理
            case R.id.type_jx://即将揭晓
                tag = TYPE_JX;
                type = 10;
                break;
            case R.id.type_rq: //人气
                tag = TYPE_RQ;
                type = 20;
                break;
        }
        params.put("type", type);
        params.put("curr", page);
        DreamApplication.getApp().getDreamNet().netJsonPost(tag, ProtocolUrl.SHOPLIST, params);
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

    // 最新揭晓显示的数据
    @ItemPresentationModel(value = GoodsItemPM.class, factoryMethod = "createItemPM")
    public List<PublishBean> getPublishBeans() {
        return publishBeans;
    }

    // 人气，最新，即将揭晓，价格 要显示的数据
    @ItemPresentationModel(value = GoodsItemPM.class, factoryMethod = "createItemPM")
    public List<OtherGoodBean> getGoods() {
        return goods;
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
        this.publishBeans.clear();
        this.publishBeans.addAll(publishBeans);
        getPresentationModelChangeSupport().firePropertyChange("publishBeans");
    }

    /**
     * 更新 人气，即将揭晓 等商品
     * @param goods
     */
    public void setGoods(List<OtherGoodBean> goods){
        if(goods == null) return;
        this.goods.clear();
        this.goods.addAll(goods);
        getPresentationModelChangeSupport().firePropertyChange("goods");
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
