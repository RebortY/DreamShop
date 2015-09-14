package com.dream.main.infoview.jiexiao;

import com.dream.R;
import com.dream.main.base.BaseActivity;

/**
 * Created by yangll on 15/9/14.
 * 往期揭晓
 */
public class JiexiaoActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.jiexiao_layout;
    }

    @Override
    public Object initPM() {
        return new JiexiaoPM();
    }
}
