package com.dream.main.infoview;

import com.dream.main.DreamApplication;
import com.dream.views.uitra.MaterialPullRefreshEvent;
import com.github.snowdream.android.util.Log;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

/**
 * Created by yangll on 15/9/13.
 * 商品详情PM
 */
@PresentationModel
public class GoodInfoPM implements HasPresentationModelChangeSupport{

    //商品tiitle
    private String title ="ceshi";
    //商品Url
    private String url = "file://drawable/R.drawable.ic_launcher";

    private boolean pullEnable = false;

    GoodInfoView view = null;
    PresentationModelChangeSupport changeSupport;
    public GoodInfoPM(GoodInfoView view) {
        this.view = view;
//        DreamApplication.getApp().eventBus().register(this);
        changeSupport = new PresentationModelChangeSupport(this);
    }

    //设置传入的 商品ID
    public void setGood(String id){
        Log.d(" goodinfo    goodid = "+id);
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    //我参与的， 图文详情
    public void onClicks(ClickEvent clickEvent){
        view.onClick(clickEvent.getView());
    }

    public void unRegist(){
        DreamApplication.getApp().eventBus().unregister(this);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent  event){

    }

    public boolean isPullEnable() {
        return pullEnable;
    }

    public void setPullEnable(boolean pullEnable) {
        this.pullEnable = pullEnable;
        changeSupport.firePropertyChange("pullEnable");
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
