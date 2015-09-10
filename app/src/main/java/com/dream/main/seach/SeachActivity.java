package com.dream.main.seach;

import com.dream.R;
import com.dream.main.base.BaseActivity;

/**
 * Created by yangll on 15/9/9.
 */
public class SeachActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.seachlayout;
    }

    @Override
    public Object initPM() {
        return new SeachPM();
    }
}
