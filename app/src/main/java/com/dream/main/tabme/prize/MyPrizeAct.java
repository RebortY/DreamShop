package com.dream.main.tabme.prize;

import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 21:59
 * 我获得的奖品
 */
public class MyPrizeAct extends BaseActivity implements BaseActView {
    @Override
    public void setOnClickView(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_prize;
    }

    @Override
    public Object initPM() {
        return null;
    }
}
