package com.dream.main.infoview;

import com.alibaba.fastjson.JSON;
import com.dream.bean.goodinfo.GoodInfo;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/9/13.
 * 商品详情PM
 */
@PresentationModel
public class GoodInfoPM implements HasPresentationModelChangeSupport {

    //商品tiitle
    private String title = "ceshi";
    //商品Url
    private String url = "file://drawable/R.drawable.ic_launcher";

    private final String GOODINFOTAG = "GOODINFO_TAG";

    private float aspectRatio = 1.5f;

    GoodInfoView view = null;
    PresentationModelChangeSupport changeSupport;

    MaterialPullRefreshEvent tempEvent = null;
    private String goodId;
    GoodInfo info = null;

    public GoodInfoPM(GoodInfoView view) {
        this.view = view;
        DreamApplication.getApp().eventBus().register(this);
        changeSupport = new PresentationModelChangeSupport(this);
    }

    //设置传入的 商品ID
    public void setGood(String id) {
        goodId = id;
    }

    @Subcriber(tag = GOODINFOTAG, threadMode = ThreadMode.MainThread)
    public void resphandle(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            JSONObject obj = (JSONObject) response.getResp();
            try {
                String str = (obj.getJSONObject("data")).getJSONObject("shop").toString();
                GoodInfo info = JSON.parseObject(str, GoodInfo.class);
                this.info = info;
                setTitle(info.getTitle());
                setUrl(info.getThumb());
                view.setCanyuTextCount(info.getMe_gonumber());
            } catch (JSONException E) {
                ToastUtil.show("商品详情解析失败");
            }
        } else {
            ToastUtil.show("获取详情失败");
        }
        if(tempEvent != null)
        view.stopRefresh(tempEvent.getView());
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
        changeSupport.firePropertyChange("title");
    }

    public void setUrl(String url) {
        this.url = url == null ? this.url : url;
        changeSupport.firePropertyChange("url");
    }

    //我参与的， 图文详情
    public void onClicks(ClickEvent clickEvent) {
        view.onClick(clickEvent.getView());
    }

    public void unRegist() {
        DreamApplication.getApp().eventBus().unregister(this);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event)
    {
        tempEvent = event;
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", goodId);
        DreamApplication.getApp().getDreamNet().netJsonPost(GOODINFOTAG, ProtocolUrl.GOODINFO, params);
    }

    public GoodInfo getGoodInfo(){
        return info;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
