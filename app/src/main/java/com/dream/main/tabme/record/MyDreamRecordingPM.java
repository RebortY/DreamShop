package com.dream.main.tabme.record;

import com.alibaba.fastjson.JSON;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.bean.MyPrizeInfo;
import com.dream.main.DreamApplication;
import com.dream.main.tabme.prize.MyPrizeItemPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;
import com.dream.views.xviews.XLoadEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.adapterview.ItemClickEvent;

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
public class MyDreamRecordingPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_RECORD_ING = "TAG_GET_RECORD_ING";


    private List<MyDreamRecordingInfo> data = new ArrayList<>();

    private boolean loadEnable = true;
    private MaterialPullRefreshEvent tempPullEvent;
    private XLoadEvent tempLoadEvent;

    PresentationModelChangeSupport changeSupport;

    MyDreamRecordView view;

    private int page = 1;
    private int size = 10;
    private int total = 0;

    MyDreamRecordingPM(MyDreamRecordView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);
    }

    private void getDataPage() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);
        map.put("page", page);
        map.put("size", size);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_RECORD_ING, ProtocolUrl.SHOP_MYBAY, map);
    }


    @Subcriber(tag = TAG_GET_RECORD_ING, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("rows");
                total = obj.getJSONObject("data").getInt("total");
                List<MyDreamRecordingInfo> commentInfos = JSON.parseArray(array.toString(), MyDreamRecordingInfo.class);
                setData(commentInfos);
            } catch (JSONException e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }

        stopPullOrRefresh();
    }

    private void stopPullOrRefresh() {
        if (tempPullEvent != null)
            view.stopRefresh(tempPullEvent.getView());
        if (tempLoadEvent != null)
            view.stopLoad(tempLoadEvent.getView());
    }

    public void setData(List<MyDreamRecordingInfo> data) {
        if (page == 1) this.data.clear();
        this.data.addAll(data);
        getPresentationModelChangeSupport().firePropertyChange("data");
    }

    @ItemPresentationModel(value = MyDreamRecordingItemsPM.class, factoryMethod = "myDreamRecordingItemsPM")
    public List<MyDreamRecordingInfo> getData() {
        return data;
    }

    public MyDreamRecordingItemsPM myDreamRecordingItemsPM() {
        return new MyDreamRecordingItemsPM();
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }

    /**
     * 下拉刷新
     */
    public void refresh(MaterialPullRefreshEvent event) {
        if (!loadEnable) loadable(true);
        tempPullEvent = event;
        page = 1;
        getDataPage();
    }

    public void onload(XLoadEvent event) {
        tempLoadEvent = event;
        if (loadEnable && total < page * size) {
            loadable(false);
            return;
        }
        page++;
        getDataPage();
    }

    private void loadable(boolean enable) {
        loadEnable = enable;
        getPresentationModelChangeSupport().firePropertyChange("loadEnable");
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public void clickItem(ItemClickEvent event) {
        MyDreamRecordingInfo info = (MyDreamRecordingInfo)event.getParent().getAdapter().getItem(event.getPosition());
        view.onClick(event.getView(), info);
    }
}
