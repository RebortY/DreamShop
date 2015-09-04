package com.dream.main.tabpublish;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.tabmain.pmbeans.OtherGoodBean;
import com.dream.main.tabmain.pmbeans.PublishBean;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.dream.views.pulltorefresh.event.DreamItemClick;
import com.dream.views.uitra.MaterialPullRefreshEvent;
import com.litesuits.android.log.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;

import java.util.ArrayList;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/20.
 */
@PresentationModel
public class PublishPM extends AbstractPM {

    private static final String PUBLISHTAG = "TAG_publish";
    private ArrayList<GoodItemBean> goods = new ArrayList<GoodItemBean>();
    private PublishView publishView = null;
    MaterialPullRefreshEvent tempEvent;
    public PublishPM(PublishView view) {
        publishView = view;
        DreamApplication.getApp().eventBus().register(this);
    }

    private void refresh(){
        DreamApplication.getApp().getDreamNet().netJsonGet(PUBLISHTAG, ProtocolUrl.PUBLISH);
    }


    @Subcriber(tag = PUBLISHTAG, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            respGoods(response,PUBLISHTAG);
        } else {
            ToastUtil.show(R.string.net_error);
        }
        if(tempEvent != null)
        publishView.stopRefresh(tempEvent.getView());
    }

    @ItemPresentationModel(value = GoodItemPM.class)
    public ArrayList<GoodItemBean> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<GoodItemBean> goods) {
        this.goods = goods;
    }

    //处理返回商品
    private void respGoods(NetResponse response, String tag) {
        String jsonStr = null;
        try {
            JSONObject jsonObj = ((JSONObject) response.getResp()).getJSONObject("data");
            jsonStr = jsonObj.getJSONArray("list").toString();
            DreamApplication.getApp().getSharedPreferences().add(tag, jsonStr);
            List<Good> jgoods = JSON.parseArray(jsonStr, Good.class);
            ArrayList<GoodItemBean> publishBeans = new ArrayList<GoodItemBean>();
            for (Good g : jgoods) {
                GoodItemBean pb = new GoodItemBean(g);
                publishBeans.add(pb);
            }
            goods.addAll(publishBeans);
            getPresentationModelChangeSupport().firePropertyChange("goods");
        } catch (JSONException ex) {
            Log.v("JSON 格式化错误 ---->" + jsonStr);
        }
    }

    @Override
    public void refresh(MaterialPullRefreshEvent event) {
        tempEvent = event;
        refresh();
    }

    public void goodsItemClick(ItemClickEvent event){
        GoodItemBean bean =  (GoodItemBean)event.getParent().getAdapter().getItem(event.getPosition());
        ToastUtil.show(bean.getManey());
    }

    public void unregister(){
        DreamApplication.getApp().eventBus().unregister(this);
    }
}
