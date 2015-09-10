package com.dream.main.tabme.record;

import com.alibaba.fastjson.JSON;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
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
 * 15/9/10 20:16
 * 已揭晓
 */
@PresentationModel
public class MyDreamRecordunFragmentPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_RECORD_UN = "TAG_GET_RECORD_UN";

    private boolean loadEnable = false;

    private List<MyDreamRecordUnInfo> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;
    PresentationModelChangeSupport changeSupport;

    MyDreamRecordView view;

    MyDreamRecordunFragmentPM(MyDreamRecordView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);

        getDatas();
    }

    private void getDatas() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", 3);
        map.put("page", 1);
        map.put("size", 10);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_RECORD_UN, ProtocolUrl.SHOP_MYBAY_UN, map);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getData();
    }


    @Subcriber(tag = TAG_GET_RECORD_UN, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("rows");
                List<MyDreamRecordUnInfo> commentInfos = JSON.parseArray(array.toString(), MyDreamRecordUnInfo.class);
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

    @ItemPresentationModel(value = MyDreamRecordUnFragmentItemPM.class)
    public List<MyDreamRecordUnInfo> getData() {
        return data;
    }

}
