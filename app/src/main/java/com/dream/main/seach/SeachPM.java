package com.dream.main.seach;

import com.alibaba.fastjson.JSON;
import com.dream.bean.SeachGood;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.xviews.XLoadEvent;
import com.paging.gridview.PagingGridView;

import org.apache.commons.lang.StringUtils;
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
 * Created by yangll on 15/9/9.
 */
@PresentationModel
public class SeachPM implements HasPresentationModelChangeSupport {

    private boolean loadEnable = true;
    private String input = "手机";

    PresentationModelChangeSupport changeSupport = null;
    private final String SEACHTAG = "SEACHTAG";
    List<SeachGood> goodList = new ArrayList<>();
    XLoadEvent tempevent = null;

    int page = 1;
    int count = 5;
    int total = 0;

    SeachView view;

    public SeachPM(SeachView view) {
        this.view = view;
        changeSupport = new PresentationModelChangeSupport(this);
        DreamApplication.getApp().eventBus().register(this);
    }

    private void getSeach() {
        if (StringUtils.isBlank(input)) {
            ToastUtil.show("请输入搜索内容");
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", input);
        params.put("page", page);
        params.put("size", count);
        DreamApplication.getApp().getDreamNet().netJsonPost(SEACHTAG, ProtocolUrl.SEACH, params);
    }

    public SeachEmptyPM getEmptyPM() {
        return new SeachEmptyPM();
    }

    //搜索按钮
    public void seach(ClickEvent event) {
        getSeach();
    }

    @Subcriber(tag = SEACHTAG, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {

            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONObject dataObj = obj.getJSONObject("data");
                total = dataObj.getInt("total");
                String strArray = dataObj.getJSONArray("list").toString();

                List<SeachGood> goodList = JSON.parseArray(strArray, SeachGood.class);
                Result result = new Result();
                result.setGoods(goodList);

                if (tempevent != null) {
                    ((PagingGridView) tempevent.getView()).onFinishLoading(false, goodList);
                    if (page * count > total) setLoadEnable(false);
                    else setLoadEnable(true);
                }else{
                    view.setData(result);
                }
            } catch (JSONException ex) {
                ToastUtil.show("结果解析失败");
            }
        } else {
            ToastUtil.show("获取结果失败");
        }
    }

    @ItemPresentationModel(value = SeachItemPM.class)
    public List<SeachGood> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<SeachGood> goodList) {
        this.goodList = goodList;
    }

    //加载更多
    public void onGridLoad(XLoadEvent event) {
        tempevent = event;
        if (page * count >= total) return;
        page++;
        getSeach();
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
        changeSupport.firePropertyChange("loadEnable");
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    //点击搜索结果
    public void clickItem(ItemClickEvent clickEvent) {

    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
