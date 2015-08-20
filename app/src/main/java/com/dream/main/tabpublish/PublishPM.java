package com.dream.main.tabpublish;

import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/20.
 */
@PresentationModel
public class PublishPM extends AbstractPM {

    private static final String PUBLISHTAG = "TAG_publish";

    public PublishPM(String title) {
        this.title = title;
        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonGet(PUBLISHTAG , ProtocolUrl.PUBLISH);
    }

    private String title = "首页";

    public String getTitle() {
        return title;
    }

    @Subcriber(tag = PUBLISHTAG , threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response){
        if(response.getRespType() == NetResponse.SUCCESS){
            title = response.getResp().toString();
        }else{
            title += "请求失败";
        }
        getPresentationModelChangeSupport().firePropertyChange("title");
    }

}
