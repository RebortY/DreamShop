package com.dream.main.tabme.set;

import android.app.Activity;
import android.content.Context;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;

import org.json.JSONException;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 20:49
 */
@PresentationModel
public class SetPM extends AbstractPM {

    public final String TAG_LOGIN_OUT = "TAG_LOGIN_OUT";

    BaseActView baseActView;
    Activity mContext;


    public SetPM(BaseActView baseActView1, Activity context){
        this.baseActView = baseActView1;
        this.mContext = context;
        DreamApplication.getApp().eventBus().register(this);
    }

    public void onClicks(ClickEvent event){

        switch (event.getView().getId()){

            case R.id.layoutItem_help:
                break;
            case R.id.layoutItem_fwxy:
                break;
            case R.id.layoutItem_cjwt:
                break;
            case R.id.btLogOut:
                DreamApplication.getApp().getDreamNet().netJsonPost(TAG_LOGIN_OUT, ProtocolUrl.LOGOUT, new HashMap<>());
                break;
        }
    }

    @Subcriber(tag = TAG_LOGIN_OUT, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                DreamApplication.getApp().setAuthUser(null);
                mContext.finish();
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }

    }
}
