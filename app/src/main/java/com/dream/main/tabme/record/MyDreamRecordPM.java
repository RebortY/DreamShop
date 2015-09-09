package com.dream.main.tabme.record;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 23:03
 */
@PresentationModel
public class MyDreamRecordPM implements HasPresentationModelChangeSupport {

    private final String TAG_GET_RECORD_ING = "TAG_GET_RECORD_ING";

    private MaterialPullRefreshEvent tempPullEvent;
    PresentationModelChangeSupport changeSupport;

    MyDreamRecordView view;
    MyDreamRecordPM(MyDreamRecordView baseActViews){

        changeSupport = new PresentationModelChangeSupport(this);

        this.view = baseActViews;
        DreamApplication.getApp().eventBus().register(this);

        getData();
    }

    private void getData(){

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("state",1);
        map.put("page",1);
        map.put("size",10);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_RECORD_ING, ProtocolUrl.SHOP_MYBAY, map);
    }

    //下拉刷新
    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getData();
    }

    @Subcriber(tag = TAG_GET_RECORD_ING, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {

        } else {
            ToastUtil.show(R.string.net_error);
        }
        stopPullOrRefresh();
    }

    private void stopPullOrRefresh() {
        if (tempPullEvent != null)
            view.stopRefresh(tempPullEvent.getView());
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
