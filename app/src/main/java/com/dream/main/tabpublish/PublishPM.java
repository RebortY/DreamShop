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


    public PublishPM() {
        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(PUBLISHTAG, ProtocolUrl.PUBLISH);
    }


    @Subcriber(tag = PUBLISHTAG, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            respGoods(response,PUBLISHTAG);
        } else {
            ToastUtil.show(R.string.net_error);
        }
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
        JSONObject jsonObj = (JSONObject) response.getResp();
        String jsonStr = null;
        try {
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

    public void goodsItemClick(ItemClickEvent event){

    }
}
