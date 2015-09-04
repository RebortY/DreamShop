package com.dream.main.tabme;

import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/4 14:26
 * 收货地址列表
 */
public class AddressAct extends BaseActivity implements BaseActView {
    @Override
    public void setOnClickView(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public Object initPM() {
        return new AddressPM(this, this
        );
    }

    @Override
    public void initView() {

    }
}
