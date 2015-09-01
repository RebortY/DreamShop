package com.dream.main.tabme;

import android.view.View;

import com.dream.R;
import com.dream.bean.AuthUser;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.util.StringUtils;
import com.dream.views.layout.LayoutItem;

import butterknife.Bind;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/29 00:13
 * 用户信息
 */
public class UserInfoAct extends BaseActivity implements BaseActView{

    @Bind(R.id.layoutItem_head)
    LayoutItem layoutItemUser;//头像

    @Bind(R.id.layoutItem_username)
    LayoutItem layoutItemUserName;//名字

    @Bind(R.id.layoutItem_signature)
    LayoutItem layoutItemSignature;//个性签名

    @Bind(R.id.layoutItem_email)
    LayoutItem layoutItemEmail;//邮箱

    @Bind(R.id.layoutItem_phone)
    LayoutItem layoutItemPhone;//手机号

    UserInfoPM userInfoPM;

    @Override
    public void setOnClickView(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public Object initPM() {
        userInfoPM = new UserInfoPM(this);
        return userInfoPM;
    }

    @Override
    public void initView() {

        AuthUser authUser = DreamApplication.getApp().getUser();

        layoutItemUser.leftImg.setVisibility(View.GONE);
        layoutItemUser.centreImg.setVisibility(View.VISIBLE);
        layoutItemUserName.leftImg.setVisibility(View.GONE);
        layoutItemSignature.leftImg.setVisibility(View.GONE);
        layoutItemEmail.leftImg.setVisibility(View.GONE);
        layoutItemPhone.leftImg.setVisibility(View.GONE);

        if(authUser != null){
            if(!StringUtils.isEmpty(authUser.getUsername())){
                layoutItemUserName.rightText.setText(authUser.getUsername());
            }
            if(!StringUtils.isEmpty(authUser.getQianming())){
                layoutItemSignature.rightText.setText(authUser.getQianming());
            }
            if(!StringUtils.isEmpty(authUser.getEmail())){
                layoutItemEmail.rightText.setText(authUser.getEmail());
            }
            if(!StringUtils.isEmpty(authUser.getMobile())){
                layoutItemPhone.rightText.setText(authUser.getMobile());
            }
        }

    }
}
