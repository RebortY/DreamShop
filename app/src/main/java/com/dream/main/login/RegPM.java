package com.dream.main.login;

import com.dream.main.base.BaseActView;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 21:56
 */
@PresentationModel
public class RegPM extends AbstractPM {

    BaseActView baseActView;

    String userPhone;

    String smsCode;

    String psd;

    String psdConfirm;


    public RegPM(BaseActView baseActViews){
        this.baseActView = baseActViews;
    }

    public String getPsdConfirm() {
        return psdConfirm;
    }

    public void setPsdConfirm(String psdConfirm) {
        this.psdConfirm = psdConfirm;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public void onClicks(ClickEvent event){

        baseActView.setOnClickView(event.getView());
    }
}
