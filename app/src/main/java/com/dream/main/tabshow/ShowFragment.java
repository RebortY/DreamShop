package com.dream.main.tabshow;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.tabmain.TabMainPM;

/**
 * Created by yangll on 15/8/16.
 */
public class ShowFragment extends AbstractTabFragment implements ShowView {

    @Override
    public int getlayoutId() {
        return R.layout.fragment_show;
    }

    @Override
    public Object getPM() {
        return new ShowPM("晒单");
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }
}
