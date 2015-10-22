package com.dream.main.tabme.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dream.R;
import com.dream.bean.AddressEditBean;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActivity;
import com.dream.main.goodpay.GoodPayActivity;
import com.dream.views.uitra.MaterialPullRefresh;
import java.util.ArrayList;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:10
 */
public class AddressActivity extends BaseActivity implements AddressView {

    public static final int ACT_CODE = 1001;

    AddressActivityPM addressActivityPM;
    ArrayList<Good> goods;
    public static final String CODE_RESULT_EDIT = "CODE_RESULT_EDIT";//提交完成post tag
    @Override
    public int getLayoutId() {
        return R.layout.activity_address_new;
    }

    @Override
    public Object initPM() {if (addressActivityPM == null) {
            addressActivityPM = new AddressActivityPM(this, this);
        }
        return addressActivityPM;
    }


    @Override
    public void onClick(View view, AddressListItemInfo.DataEntity.ListEntity form) {
        if (view.getId() == R.id.img_edit || view.getId() == R.id.bt_commit) {
            imgClick(form);
        }
    }

    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void intentInfoView(AddressListItemInfo.DataEntity.ListEntity addressInfo) {
        if(goods != null){
            Intent intent = new Intent(this , GoodPayActivity.class);
            intent.putParcelableArrayListExtra(GoodPayActivity.GOODLIST, goods);
            intent.putExtra(AddressActivity.class.getName(), addressInfo);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void setAttIntent(Intent intent) {
        goods =  intent.getParcelableArrayListExtra(GoodPayActivity.GOODLIST);
        addressActivityPM = new AddressActivityPM(this, this);
        if(goods != null){
            addressActivityPM.setTitleBar("选择收货地址");
        }else {
            addressActivityPM.setTitleBar(getResources().getString(R.string.tv_user_address));
        }
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

        startActivityForResult(intent, ACT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addressActivityPM.getDatas();
    }
}
