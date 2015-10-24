package com.dream.main.tabshow;

import com.alibaba.fastjson.JSON;
import com.dream.bean.CommentInfo;
import com.dream.main.DreamApplication;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/9/7.
 */
@PresentationModel
public class CommentPM extends TitleBarPM{

    private final String TAG = "COMMENTLIST";
    private final String ADDCOMMENT ="ADDCOMMENT";
    //评论内容
    private String content;
    private List<CommentInfo> data = new ArrayList<>();

    private String showId ;
    MaterialPullRefreshEvent tempEvent;

    PresentationModelChangeSupport changeSupport;
    CommentView view;

    private boolean loadEnable = false;

    public CommentPM(CommentView view) {
        changeSupport = new PresentationModelChangeSupport(this);
        DreamApplication.getApp().eventBus().register(this);
        this.view = view;
        setTitleBar("评论");
    }

    public void setShowId(String id) {
        showId = id;
        HashMap<String, Object> params = new HashMap<>();
        params.put("sd_id", Integer.parseInt(id));
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG, ProtocolUrl.COMMENTLIST, params);
    }

    public void send(ClickEvent event) {
        if(StringUtils.isBlank(content)){
            ToastUtil.show("内容不能为空");
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("sd_id", Integer.parseInt(showId));
        params.put("content",content);
        DreamApplication.getApp().getDreamNet().netJsonPost(ADDCOMMENT, ProtocolUrl.COMMENTADD, params);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempEvent = event;
        setShowId(showId);
    }

    @Subcriber(tag = TAG, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                List<CommentInfo> commentInfos = JSON.parseArray(array.toString(), CommentInfo.class);
                data.clear();
                data.addAll(commentInfos);
                changeSupport.firePropertyChange("data");
            } catch (JSONException e) {
                ToastUtil.show("评论列表 数据异常");
            }
        } else {
            ToastUtil.show("获取评论列表失败");
        }
        if(tempEvent != null)
        view.stopRefresh(tempEvent.getView());
    }

    @Subcriber(tag = ADDCOMMENT, threadMode = ThreadMode.MainThread)
    public void commentHandler(NetResponse response){
        if(response.getRespType() == NetResponse.SUCCESS){
            JSONObject obj = (JSONObject)response.getResp();
            try{
                String str =  obj.getJSONObject("data").getJSONObject("huifu").toString();
                CommentInfo info = JSON.parseObject(str,CommentInfo.class);
                data.add(0,info);
                changeSupport.firePropertyChange("data");
            }catch(JSONException e){

            }
            ToastUtil.show("评论成功");
            content = null;
            changeSupport.firePropertyChange("content");
        }else{
            ToastUtil.show("评论失败");
        }
    }

    @ItemPresentationModel(value = CommentItemPM.class)
    public List<CommentInfo> getData() {
        return data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void unregister() {
        DreamApplication.getApp().eventBus().unregister(this);
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
