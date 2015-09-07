package com.dream.main;

import com.alibaba.fastjson.JSON;
import com.dream.bean.Category;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.ArrayList;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/9/7.
 */
@PresentationModel
public class NavigationPM implements HasPresentationModelChangeSupport{

    List<Category> categorys = new ArrayList<>();
    private final String TAG = "CATEGORYS";

    PresentationModelChangeSupport changeSupport = null;
    public NavigationPM() {
        changeSupport = new PresentationModelChangeSupport(this);
        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(TAG, ProtocolUrl.CATEGORYS);
    }

    @ItemPresentationModel(value = NavigationItemPM.class)
    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys.clear();
        this.categorys.addAll(categorys);
        changeSupport.firePropertyChange("categorys");
    }

    @Subcriber(tag = TAG , threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response){
        if(response.getRespType() == NetResponse.SUCCESS){
            try{
                JSONObject obj = (JSONObject)response.getResp();
                JSONArray list =  obj.getJSONObject("data").getJSONArray("list");
                List<Category> categories = JSON.parseArray(list.toString(), Category.class);
                setCategorys(categories);
            }catch(JSONException e){
                ToastUtil.show("分类获取失败");
            }
        }
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public void unregister(){
        DreamApplication.getApp().eventBus().unregister(this);
    }
}
