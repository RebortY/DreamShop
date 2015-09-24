package com.dream.main.tabme.prize;

import com.alibaba.fastjson.JSON;
import com.dream.bean.GoodForm;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyPrizeInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.StopRefreshView;
import com.dream.main.tabme.record.MyDreamRecordUnFragmentItemPM;
import com.dream.main.tabme.record.MyDreamRecordView;
import com.dream.main.tabshow.items.ShowItemPM;
import com.dream.main.titlebar.TitleBarPM;
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
public class MyPrizePM extends TitleBarPM {

    private final String TAG_GET_MY_PRIZE = "TAG_GET_MY_PRIZE";

    private List<MyPrizeInfo> data = new ArrayList<>();

    MyPrizeView view;

    private boolean loadEnable = true;
    private MaterialPullRefreshEvent tempPullEvent;
    private XLoadEvent tempLoadEvent;

    private int page = 1;
    private int size = 10;
    private int total = 0;

    MyPrizePM(MyPrizeView baseActViews) {

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);
    }

    private void getDataPage() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("page", page);
        map.put("size", size);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_MY_PRIZE, ProtocolUrl.USER_ORDER, map);
    }


    @Subcriber(tag = TAG_GET_MY_PRIZE, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                total = obj.getJSONObject("data").getInt("total");
                List<MyPrizeInfo> commentInfos = JSON.parseArray(array.toString(), MyPrizeInfo.class);
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

    public void setData(List<MyPrizeInfo> data) {
        if (page == 1) this.data.clear();
        this.data.addAll(data);
        getPresentationModelChangeSupport().firePropertyChange("data");
    }

    @ItemPresentationModel(value = MyPrizeItemPM.class, factoryMethod = "myPrizeItemPM")
    public List<MyPrizeInfo> getData() {
        return data;
    }

    public MyPrizeItemPM myPrizeItemPM() {
        return new MyPrizeItemPM(view);
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
    public String getTitleBar() {
        return "我获得的奖品";
    }
}
