package com.dream.main.infoview.showgood;

import android.content.Intent;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.base.BaseActivity;

/**
 * Created by yangll on 15/9/8.
 * 晒单详情
 */
public class ShowInfoActivity extends BaseActivity {

    public static final String  GOODFORM = "SHOWGOODFORM";
    private GoodForm good = null;
    ShowInfoPM pm = null;

    @Override
    public void setAttIntent(Intent intent) {
        good = (GoodForm)intent.getSerializableExtra(GOODFORM);
    }

    @Override
    public Object initPM()
    {
        pm = new ShowInfoPM();
        pm.setGood(good);
        return pm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.showinfolayout;
    }

}
