package com.dream.main.infoview.canyu;

import android.content.Intent;

import com.dream.R;
import com.dream.bean.goodinfo.RecordsEntity;
import com.dream.main.base.BaseActivity;

import java.util.List;

/**
 * Created by yangll on 15/9/14.
 * 本期参与记录
 */
public class BenqiJoinActivity extends BaseActivity{

    List<RecordsEntity> datas = null;
    public static final String CANYU = "CANYU_LIST";
    @Override
    public void setAttIntent(Intent intent) {
        datas = intent.getParcelableArrayListExtra(CANYU);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jiexiao_layout;
    }

    @Override
    public Object initPM() {
        BenqiJoinPM pm = new BenqiJoinPM();
        pm.setData(datas);
        return pm;
    }
}
