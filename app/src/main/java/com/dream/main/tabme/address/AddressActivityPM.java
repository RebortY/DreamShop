package com.dream.main.tabme.address;

import com.alibaba.fastjson.JSON;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.StopRefreshView;
import com.dream.main.tabme.record.MyDreamRecordView;
import com.dream.main.tabme.record.MyDreamRecordingItemsPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:27
 */
@PresentationModel
public class AddressActivityPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_ADDRESS = "TAG_GET_ADDRESS";

    private boolean loadEnable = false;

    private List<AddressListItemInfo.DataEntity.ListEntity> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;
    PresentationModelChangeSupport changeSupport;

    StopRefreshView view;

    AddressActivityPM( StopRefreshView views) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = views;
        DreamApplication.getApp().eventBus().register(this);

        getDatas();
    }

    private void getDatas() {

        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_ADDRESS, ProtocolUrl.ADDRESS_LIST, new HashMap<String, Object>());
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getDatas();
    }


    @Subcriber(tag = TAG_GET_ADDRESS, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                List<AddressListItemInfo.DataEntity.ListEntity> commentInfos = JSON.parseArray(array.toString(), AddressListItemInfo.DataEntity.ListEntity.class);
                data.clear();
                data.addAll(commentInfos);
                changeSupport.firePropertyChange("data");
            } catch (JSONException e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
        if (tempPullEvent != null)
            view.stopRefresh(tempPullEvent.getView());


    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }

    @ItemPresentationModel(value = AddressListItemPM.class)
    public List<AddressListItemInfo.DataEntity.ListEntity> getData() {
        return data;
    }
}
