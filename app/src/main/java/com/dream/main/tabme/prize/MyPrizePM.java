package com.dream.main.tabme.prize;

import com.alibaba.fastjson.JSON;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyPrizeInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.StopRefreshView;
import com.dream.main.tabme.record.MyDreamRecordUnFragmentItemPM;
import com.dream.main.tabme.record.MyDreamRecordView;
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
 * 15/9/14 22:05
 */
@PresentationModel
public class MyPrizePM extends AbstractPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_MY_PRIZE = "TAG_GET_MY_PRIZE";

    private boolean loadEnable = false;

    private List<MyPrizeInfo> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;
    PresentationModelChangeSupport changeSupport;

    StopRefreshView view;

    MyPrizePM(StopRefreshView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);

        getDatas();
    }

    private void getDatas() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", 1);
        map.put("size", 10);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_MY_PRIZE, ProtocolUrl.USER_ORDER, map);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getDatas();
    }


    @Subcriber(tag = TAG_GET_MY_PRIZE, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                List<MyPrizeInfo> commentInfos = JSON.parseArray(array.toString(), MyPrizeInfo.class);
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

    @ItemPresentationModel(value = MyPrizeItemPM.class)
    public List<MyPrizeInfo> getData() {
        return data;
    }

}
