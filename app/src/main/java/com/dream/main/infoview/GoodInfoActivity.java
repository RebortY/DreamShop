package com.dream.main.infoview;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActivity;
import com.dream.util.ToastUtil;

/**
 * Created by yangll on 15/9/4.
 * 商品详细页
 */
public class GoodInfoActivity extends BaseActivity implements GoodInfoView{

    GoodInfoPM pm = null;
    public static final String GOODID = "GOODid";
    String goodId;

    @Override
    public void setAttIntent(Intent intent) {
       goodId = intent.getStringExtra(GOODID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.goodinfo;
    }

    @Override
    public Object initPM() {
        pm = new GoodInfoPM(this);
        pm.setGood(goodId);
        return pm;
    }

    @Override
    public void onClick(View view) {
        ToastUtil.show("点击到了");
    }
}
