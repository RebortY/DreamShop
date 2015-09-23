package com.dream.main.tabme.account;

import com.alibaba.fastjson.JSON;
import com.dream.bean.AccountChongzhiInfo;
import com.dream.bean.AccountXiaofeiInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.StopRefreshView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/13 18:20
 */
@PresentationModel
public class XiaofeiDetailPM extends AbstractPM {
    private final String TAG_ACCOUNT_XIAOFEI = "TAG_ACCOUNT_XIAOFEI";

    private boolean loadEnable = false;

    private List<AccountXiaofeiInfo> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;

    private XLoadEvent tempLoadEvent;

    PresentationModelChangeSupport changeSupport;

    StopRefreshView view;

    int state = 1;

    int page = 1;

    int size = 10;

    private int total = 0;

    XiaofeiDetailPM(StopRefreshView baseActViews) {

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);

        getDataPage();
    }

    private void getDataPage() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", state);
        map.put("page", page);
        map.put("size", size);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_ACCOUNT_XIAOFEI, ProtocolUrl.USER_CONSUMPTION, map);
    }


    @Subcriber(tag = TAG_ACCOUNT_XIAOFEI, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                total = obj.getJSONObject("data").getInt("total");
                List<AccountXiaofeiInfo> commentInfos = JSON.parseArray(array.toString(), AccountXiaofeiInfo.class);
                data.clear();
                data.addAll(commentInfos);
                changeSupport.firePropertyChange("data");
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
            view.stopRefresh(tempLoadEvent.getView());
    }

    public void setData(List<AccountXiaofeiInfo> data) {
        if (page == 1) this.data.clear();
        this.data.addAll(data);
        getPresentationModelChangeSupport().firePropertyChange("data");
    }


    @ItemPresentationModel(value = XiaofeiDetailItemPM.class, factoryMethod = "xiaofeiDetailItemPM")
    public List<AccountXiaofeiInfo> getData() {
        return data;
    }

    public XiaofeiDetailItemPM xiaofeiDetailItemPM() {
        return new XiaofeiDetailItemPM();
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
}
