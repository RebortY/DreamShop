package com.dream.main.tabme;

import android.content.Context;
import android.content.Intent;

import com.dream.R;
import com.dream.main.base.BaseActView;
import com.dream.main.login.LoginAct;
import com.dream.main.login.RegAct;
import com.dream.main.tabme.record.MyDreamRecordAct;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM{

    BaseActView meFragmentView;
    Context mContext;

    public MEPM(Context context, BaseActView meView) {
        this.meFragmentView = meView;
        this.mContext = context;
    }

    private String userName;//用户名


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickView(event.getView());

        switch (event.getView().getId()){
            case R.id.tv_login:
                mContext.startActivity(new Intent(mContext, LoginAct.class));
                break;
            case R.id.tv_reg:
                mContext.startActivity(new Intent(mContext, RegAct.class));
                break;
            case R.id.tv_pay:
                mContext.startActivity(new Intent(mContext, AccountPayAct.class));
                break;
            case R.id.img_hand:
//                if(DreamApplication.getApp().getUser() != null){
                mContext.startActivity(new Intent(mContext, UserInfoAct.class));
//                }
                break;
            case R.id.layoutItem_address:
                mContext.startActivity(new Intent(mContext, AddressAct.class));
                break;
            case R.id.tv_my_dream_shoptv_my:
                mContext.startActivity(new Intent(mContext, MyDreamRecordAct.class));
                break;
        }
    }

}
