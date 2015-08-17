package com.dream.main.tabme;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.tabmain.TabMainPM;

/**
 * Created by yangll on 15/8/16.
 */
public class MeFragment extends AbstractTabFragment {

    @Override
    public int getlayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public Object getPM() {
        return new TabMainPM("我的");
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }
}
