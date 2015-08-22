package com.dream.main.tabmain;

import android.nfc.Tag;

import com.alibaba.fastjson.JSON;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.views.AbstractPM;
import com.litesuits.android.log.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/15.
 */
@PresentationModel
public class TabMainPM extends AbstractPM{

    private String url = "http://m.1yuanmeng.com/statics/uploads/banner/20150730/81318274218875.png";
    private boolean circle = false;
    private TabMainView view = null;

    //轮播图
    private final String TAGSLIB = "TAG_SLIBS";

    public TabMainPM(TabMainView view) {
        this.view = view;

        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(TAGSLIB , ProtocolUrl.FOCUS);
    }

    public String getUrl() {
        return url;
    }

    @Subcriber(tag = TAGSLIB , threadMode = ThreadMode.MainThread)
    public void handleSlibs(NetResponse response){
        if(response.getRespType() == NetResponse.SUCCESS){
           //存到本地缓存中
            JSONObject jsonObj = (JSONObject)response.getResp();
            String jsonStr = null;
            try{
                jsonStr = jsonObj.getJSONArray("items").toString();
                DreamApplication.getApp().getSharedPreferences().add(TAGSLIB , jsonStr);
                List<Carousel> carousel = JSON.parseArray(jsonStr, Carousel.class);
                view.setCarouselAdapter(carousel);
            }catch(JSONException ex){
                Log.v("JSON 格式化错误 ---->"+ jsonStr);
            }
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCircle() {
        return circle;
    }

    public void unregister(){
        DreamApplication.getApp().eventBus().unregister(this);
    }

}
