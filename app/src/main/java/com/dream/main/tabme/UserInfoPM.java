package com.dream.main.tabme;

import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/29 17:06
 */

@PresentationModel
public class UserInfoPM extends TitleBarPM {

    BaseActView meFragmentView;

    private boolean circle = true;

    String url = "file://drawable/R.drawable.img_hand_def";//头像url


    public UserInfoPM(BaseActView meFragmentViews){

        DreamApplication.getApp().eventBus().register(this);
        this.meFragmentView = meFragmentViews;
    }

    public String getUrl() {
        return DreamApplication.getApp().getUser().getImg();
    }

    public boolean isCircle() {
        return circle;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickView(event.getView());
    }

    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {
        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
    }

    @Subcriber(tag = UserInfoAct.TAG_UserInfoAct_hand, threadMode = ThreadMode.MainThread)
    public void postRespHandler(String handPath) {
        url = "file://" + handPath;
        pmRefresh("url");
    }
}
