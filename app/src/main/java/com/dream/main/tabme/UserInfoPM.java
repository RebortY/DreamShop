package com.dream.main.tabme;

import com.alibaba.fastjson.JSON;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.UpLoadHeadBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.ToastUtil;
import com.dream.util.UplodUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/29 17:06
 */

@PresentationModel
public class UserInfoPM extends TitleBarPM {

    private final String CODE_HEAD_OK = "CODE_HEAD_OK";
    public static final String CODE_HEAD_OK_POST = "CODE_HEAD_OK_POST";

    BaseActView meFragmentView;

    private boolean circle = true;

    String url = "file://drawable/R.drawable.img_hand_def";//头像url

    String name;

    String signature;

    String email;

    String phone;

    UpLoadHeadBean handBean;


    public UserInfoPM(BaseActView meFragmentViews) {

        DreamApplication.getApp().eventBus().register(this);
        this.meFragmentView = meFragmentViews;
    }

    public String getUrl() {
        return DreamApplication.getApp().getUser().getImg();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getPhone() {
        return DreamApplication.getApp().getUser().getMobile();
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return DreamApplication.getApp().getUser().getUsername();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return DreamApplication.getApp().getUser().getQianming();
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return DreamApplication.getApp().getUser().getEmail();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void onClicks(ClickEvent event) {

        meFragmentView.setOnClickView(event.getView());
    }

    @Subcriber(tag = LoginTag.LOGIN, threadMode = ThreadMode.MainThread)
    public void loginRespHandler(LoginResp resp) {
        if (RespCode.SUCCESS.equals(resp.getErrorCode())) {
        } else {
            ToastUtil.show(resp.getErrorMsg());
        }
    }

    @Subcriber(tag = UplodUtil.COD_UPLOD, threadMode = ThreadMode.MainThread)
    public void postRespHandler(UpLoadHeadBean headBean) {
        updataUserInfo(headBean);
    }

    /**
     *
     * @param handBeans  null代表不是上传头像
     */
    private void updataUserInfo(UpLoadHeadBean handBeans) {

        if(handBeans != null){
            handBean = handBeans;
            url = handBean.getData().getUrl();
        }

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("username", getName());
        hashMap.put("email", getEmail());
        hashMap.put("img", url);
        hashMap.put("qianming", getSignature());
        hashMap.put("mobile", getPhone());

        DreamApplication.getApp().getDreamNet().netJsonPost(CODE_HEAD_OK, ProtocolUrl.USER_EDIT_INFO, hashMap);
    }

    @Subcriber(tag = CODE_HEAD_OK, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {

        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                ToastUtil.show("更新成功");
                Log.d("更新成功");
                if(handBean != null){//头像更新
                    DreamApplication.getApp().getUser().setImg(handBean.getData().getPath() + handBean.getData().getUrl());
//                    DreamApplication.getApp().eventBus().post(handBean, CODE_HEAD_OK_POST);
                    MEPM.changeSupport.firePropertyChange("url");
                    pmRefresh("url");
                }else{//信息资料更新

                }
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
    }

    @Override
    public String getTitleBar() {
        return "用户信息";
    }
}
