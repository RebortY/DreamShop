package com.dream.main.tabme.record;

import com.alibaba.fastjson.JSON;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
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
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 22:31
 * 正在揭晓
 */
@PresentationModel
public class MyDreamRecordingFragmentPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_RECORD_ING = "TAG_GET_RECORD_ING";

    private boolean loadEnable = false;

    private List<MyDreamRecordingInfo> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;
    PresentationModelChangeSupport changeSupport;

    MyDreamRecordView view;

    MyDreamRecordingFragmentPM(MyDreamRecordView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);

        getDatas();
    }

    private void getDatas() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);
        map.put("page", 1);
        map.put("size", 10);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_RECORD_ING, ProtocolUrl.SHOP_MYBAY, map);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getData();
    }


    @Subcriber(tag = TAG_GET_RECORD_ING, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("rows");
                List<MyDreamRecordingInfo> commentInfos = JSON.parseArray(array.toString(), MyDreamRecordingInfo.class);
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

    @ItemPresentationModel(value = MyDreamRecordingFragmentItemPM.class)
    public List<MyDreamRecordingInfo> getData() {
        return data;
    }
}
