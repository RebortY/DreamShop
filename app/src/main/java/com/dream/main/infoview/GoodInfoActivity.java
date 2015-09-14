package com.dream.main.infoview;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActivity;
import com.dream.views.layout.LayoutItem;
import com.dream.views.uitra.MaterialPullRefresh;

import butterknife.Bind;

/**
 * Created by yangll on 15/9/4.
 * 商品详细页
 */
public class GoodInfoActivity extends BaseActivity implements GoodInfoView {

    GoodInfoPM pm = null;
    public static final String GOODID = "GOODid";
    String goodId;
    @Bind(R.id.join)
    LayoutItem join;


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
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.join: //我本期参与的奖品
                break;
            case R.id.jiexiao: //往期揭晓
                break;
            case R.id.shaidanfenxiang: //晒单分享
                break;
            case R.id.canyujilu: //本期所有参与记录

                break;
        }
    }

    @Override
    public void setCanyuTextCount(int count) {
        join.setText(getResources().getString(R.string.goodInfo_canyujilu,count));
    }
}
