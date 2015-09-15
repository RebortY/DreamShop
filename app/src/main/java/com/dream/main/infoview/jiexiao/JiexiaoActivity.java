package com.dream.main.infoview.jiexiao;

import android.content.Intent;

import com.dream.R;
import com.dream.bean.goodinfo.QishulistEntity;
import com.dream.main.base.BaseActivity;

import java.util.List;

/**
 * Created by yangll on 15/9/14.
 * 往期揭晓
 */
public class JiexiaoActivity extends BaseActivity {

    List<QishulistEntity> datas = null;
    public static final String JIEXIAOLIST = "JIEXIAO_LIST";
    @Override
    public void setAttIntent(Intent intent) {
       datas = intent.getParcelableArrayListExtra(JIEXIAOLIST);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jiexiao_layout;
    }

    @Override
    public Object initPM() {
        JiexiaoPM pm = new JiexiaoPM();
        pm.setData(datas);
        return pm;
    }
}
