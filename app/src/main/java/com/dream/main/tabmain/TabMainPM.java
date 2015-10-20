package com.dream.main.tabmain;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.Category;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.main.tabmain.pmbeans.OtherGoodBean;
import com.dream.main.tabmain.pmbeans.PublishBean;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.qq.QQConfig;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.dream.views.uitra.MaterialPullRefreshEvent;
import com.dream.views.xviews.XLoadEvent;
import com.litesuits.android.log.Log;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.robobinding.widget.radiogroup.CheckedChangeEvent;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private final String TYPE_NEW = "TYPE_NEW"; //最新
    private final String TYPE_JG = "TYPE_JG"; //价格

    private int currType = R.id.type_rq;
    private int categoryId = 0;

    //主页面接口回调
    private TabMainView view = null;
    //保存一下下拉刷新临时事件
    private MaterialPullRefreshEvent tempEvent;

    private boolean hasmore = true;

    //揭晓的数据集合
    private List<PublishBean> publishBeans = new ArrayList<PublishBean>();
    // 人气， 即将 ，最新揭晓 ，价格 所需要的列表
    private List<OtherGoodBean> goods = new ArrayList<>();

    public TabMainPM(TabMainView view) {
        this.view = view;
        DreamApplication.getApp().eventBus().register(this);
        refreshAll();
    }

    public boolean isHasmore() {
        return hasmore;
    }

    @Subcriber(tag = "changeCategoryId", threadMode = ThreadMode.BackgroundThread)
    public void categoryId(Category category) {
        this.categoryId = category.getCateid();
        getGoodsByType(currType, 1, categoryId);
    }

    private void refreshAll() {
        DreamApplication.getApp().getDreamNet().netJsonGet(TAGSLIB_FOCUS, ProtocolUrl.FOCUS);
        HashMap<String , Object> params = new HashMap<>();
        params.put("curr", 1);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAGSLIB_LAST_PUBLISH, ProtocolUrl.PUBLISH,params);
        getGoodsByType(currType, 1, categoryId);
    }

    //轮播图
    @Subcriber(tag = TAGSLIB_FOCUS, threadMode = ThreadMode.MainThread)
    public void handleSlibs(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            String jsonStr = null;
            try {
                JSONObject jsonObj = ((JSONObject) response.getResp()).getJSONObject("data");
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
            respGoods(response, TAGSLIB_LAST_PUBLISH);
        } else {
            view.showToast(R.string.net_error);
        }
        stopRefresh();
    }

    //最新人气
    @Subcriber(tag = TYPE_RQ, threadMode = ThreadMode.MainThread)
    public void type_rq(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response, TYPE_RQ);
        } else {
            view.showToast(R.string.net_error);
        }
        stopRefresh();
    }

    //即将揭晓
    @Subcriber(tag = TYPE_JX, threadMode = ThreadMode.MainThread)
    public void type_jx(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response, TYPE_JX);
        } else {
            view.showToast(R.string.net_error);
        }
        stopRefresh();
    }

    //最新
    @Subcriber(tag = TYPE_NEW, threadMode = ThreadMode.MainThread)
    public void type_new(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response, TYPE_NEW);
        } else {
            view.showToast(R.string.net_error);
        }
    }

    //价格
    @Subcriber(tag = TYPE_JG, threadMode = ThreadMode.MainThread)
    public void type_jg(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            //存到本地缓存中
            respGoods(response, TYPE_JG);
        } else {
            view.showToast(R.string.net_error);
        }
    }

    //处理返回商品
    private void respGoods(NetResponse response, String tag) {
        String jsonStr = null;
        try {
            JSONObject jsonObj = ((JSONObject) response.getResp()).getJSONObject("data");
            jsonStr = jsonObj.getJSONArray("list").toString();
            DreamApplication.getApp().getSharedPreferences().add(tag, jsonStr);
            List<Good> jgoods = JSON.parseArray(jsonStr, Good.class);
            if (jgoods != null && jgoods.size() > 0) {
                DreamApplication.getApp().getdb().save(jgoods);
            }
            if (tag.equals(TAGSLIB_LAST_PUBLISH)) {
                ArrayList<PublishBean> publishBeans = new ArrayList<PublishBean>();
                int index = 0;
                for (Good g : jgoods) {
                    PublishBean pb = new PublishBean(g);
                    if (index > 2) break;
                    publishBeans.add(pb);
                    index++;
                }
                if(publishBeans.size() == 1 && publishBeans.get(0).getGood().getId() == null){ //无数据
                    publishBeans.clear();
                }
                setPublishBeans(publishBeans);
            } else {
                ArrayList<OtherGoodBean> goods = new ArrayList<OtherGoodBean>();
                for (Good g : jgoods) {
                    OtherGoodBean ogb = new OtherGoodBean(g);
                    goods.add(ogb);
                }
                if(goods.size() == 1 && goods.get(0).getGood().getId() == null){ //无数据
                    goods.clear();
                 }
                setGoods(goods);
            }
        } catch (JSONException ex) {
            Log.v("JSON 格式化错误 ---->" + jsonStr);
        }
    }

    public void radioChange(CheckedChangeEvent event){
        int id = event.getCheckedId();
        getGoodsByType(id, 1, categoryId);
    }

    /**
     * 通过选择类型
     *
     * @param id   翻页类型对应的view id
     * @param page 翻页参数
     */
    public void getGoodsByType(int id, int page, int categoryId) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String tag = TYPE_JX;
        int type = 10; //即将揭晓的类型
        switch (id) {
            case R.id.type_jg://价格
                tag = TYPE_JG;
                type = 50;
                break;
            case R.id.type_new://最新
                tag = TYPE_NEW;
                params.put("curr", page);
                DreamApplication.getApp().getDreamNet().netJsonPost(tag, ProtocolUrl.PUBLISH, params);
                return;
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
        params.put("categoryId", categoryId);
        DreamApplication.getApp().getDreamNet().netJsonPost(tag, ProtocolUrl.SHOPLIST, params);
    }

    /**
     * 点击 轮播图下面的 三个 操作按钮
     *
     * @param clickEvent
     */
    public void operClick(ClickEvent clickEvent) {
        switch (clickEvent.getView().getId()) {
            case R.id.bar_1: //30秒了解
                view.gowebView("http://mp.weixin.qq.com/s?__biz=MzI4MzAzMjgxOQ==&mid=208283358&idx=1&sn=f448f865824040c943a1d61f4257bd30#rd");
                break;
            case R.id.bar_2: //公平么
                view.gowebView("http://mp.weixin.qq.com/s?__biz=MzI4MzAzMjgxOQ==&mid=208285599&idx=1&sn=9adc331bace8976db686dcbecf1302d8#rd");
                break;
            case R.id.bar_3: //QQ 群
                view.onViewClick(clickEvent.getView());
                break;
        }
    }

    /**
     * 显示最新揭晓 全部
     */
    public void showAll(ClickEvent event) {
        DreamApplication.getApp().eventBus().post("args", "showpublishall");
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
     *
     * @param publishBeans
     */
    public void setPublishBeans(List<PublishBean> publishBeans) {
        if (publishBeans == null) return;
        this.publishBeans.clear();
        if (publishBeans.size() > 0)
            this.publishBeans.addAll(publishBeans);
        getPresentationModelChangeSupport().firePropertyChange("publishBeans");
    }

    /**
     * 更新 人气，即将揭晓 等商品
     *
     * @param goods
     */
    public void setGoods(List<OtherGoodBean> goods) {
        if (goods == null) return;
        this.goods.clear();
        if (goods.size() > 0)
            this.goods.addAll(goods);
        getPresentationModelChangeSupport().firePropertyChange("goods");
    }

    /**
     * 点击产品的事件
     *
     * @param event
     */
    public void goodsItemClick(ItemClickEvent event) {
        AbstractBean bean = (AbstractBean) event.getParent().getAdapter().getItem(event.getPosition());
        view.goGoodInfo(bean.getGood());
    }

    /**
     * 下拉刷新
     */
    public void refresh(MaterialPullRefreshEvent event) {
        tempEvent = event;
        refreshAll();
    }

    public void onload(XLoadEvent loadEvent){
        ToastUtil.show("我是 加载更多");
    }

    //由于主页面 的请求数据 比较多，所以只要有 一个返回了，就可以通知下拉刷新关闭了
    private void stopRefresh() {
        if (tempEvent != null)
            view.stopRefresh(tempEvent.getView());
    }

    public void unregister() {
        DreamApplication.getApp().eventBus().unregister(this);
    }



}
