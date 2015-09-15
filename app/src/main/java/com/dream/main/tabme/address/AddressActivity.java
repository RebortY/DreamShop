package com.dream.main.tabme.address;

import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.main.base.StopRefreshView;
import com.dream.views.uitra.MaterialPullRefresh;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:10
 */
public class AddressActivity extends BaseActivity implements StopRefreshView {

    AddressActivityPM addressActivityPM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_new;
    }

    @Override
    public Object initPM() {
        if(addressActivityPM == null){
            addressActivityPM = new AddressActivityPM(this);
        }
        return addressActivityPM;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh)view).refreshComplete();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }
}
