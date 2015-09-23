package com.dream.main.tabme.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dream.R;
import com.dream.bean.AddressEditBean;
import com.dream.bean.AddressListItemInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.main.base.StopRefreshView;
import com.dream.net.business.RespCode;
import com.dream.net.business.login.LoginResp;
import com.dream.net.business.login.LoginTag;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefresh;
import com.github.snowdream.android.util.Log;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:10
 */
public class AddressActivity extends BaseActivity implements AddressView {

    AddressActivityPM addressActivityPM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_new;
    }

    @Override
    public Object initPM() {
        if (addressActivityPM == null) {
            addressActivityPM = new AddressActivityPM(this, this);
        }
        return addressActivityPM;
    }


    @Override
    public void onClick(View view, AddressListItemInfo.DataEntity.ListEntity form) {
        if (view.getId() == R.id.img_edit) {
            imgClick(form);
        }
    }

    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void intentInfoView(AddressListItemInfo.DataEntity.ListEntity addressInfo) {
        Log.d(addressInfo.getJiedao());
        finish();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    public void imgClick(AddressListItemInfo.DataEntity.ListEntity info) {

        AddressEditBean editBean = new AddressEditBean();

        editBean.setId(info.getId());
        editBean.setShouhuoren(info.getShouhuoren());
        editBean.setSheng(info.getSheng());
        editBean.setShi(info.getShi());
        editBean.setXian(info.getXian());
        editBean.setJiedao(info.getJiedao());
        editBean.setMobile(info.getMobile());

        Intent intent = new Intent(this, AddressEditAct.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AddressActivityPM.INTENT_AddressActivityPM, editBean);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
