package com.dream.main.tabme.record;

import com.alibaba.fastjson.JSON;
import com.dream.bean.GoodForm;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
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


    private List<MyDreamRecordUnInfo> data = new ArrayList<>();

    private boolean loadEnable = true;
    private MaterialPullRefreshEvent tempPullEvent;
    private XLoadEvent tempLoadEvent;

    PresentationModelChangeSupport changeSupport;

    MyDreamRecordView view;

    private int page = 1;
    private int size = 10;
    private int total = 0;

    MyDreamRecordunFragmentPM(MyDreamRecordView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);
    }

    private void getDataPage() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", 3);
        map.put("page", page);
        map.put("size", size);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_RECORD_UN, ProtocolUrl.SHOP_MYBAY_UN, map);
    }


    @Subcriber(tag = TAG_GET_RECORD_UN, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("rows");
                total = obj.getJSONObject("data").getInt("total");
                List<MyDreamRecordUnInfo> commentInfos = JSON.parseArray(array.toString(), MyDreamRecordUnInfo.class);
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

    public void setData(List<MyDreamRecordUnInfo> data) {
        if (page == 1) this.data.clear();
        this.data.addAll(data);
        getPresentationModelChangeSupport().firePropertyChange("data");
    }

    @ItemPresentationModel(value = MyDreamRecordUnFragmentItemPM.class, factoryMethod = "myDreamRecordUnFragmentItemPM")
    public List<MyDreamRecordUnInfo> getData() {
        return data;
    }

    public MyDreamRecordUnFragmentItemPM myDreamRecordUnFragmentItemPM() {
        return new MyDreamRecordUnFragmentItemPM(view);
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
        MyDreamRecordUnInfo info = (MyDreamRecordUnInfo)event.getParent().getAdapter().getItem(event.getPosition());
        view.onClick(event.getView(), info);
    }

}
