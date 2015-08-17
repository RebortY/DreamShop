package com.dream.main.tabmain;

import android.os.Bundle;
import android.util.Log;

import com.dream.R;
import com.dream.main.AbstractTabFragment;

/**
 * Created by yangll on 15/8/16.
 *
 * Fragment 增加一些Fragment 自身的处理
 * 对应的 PM 处理网络，逻辑，数据的 处理
 */
public class TabMainFragment extends AbstractTabFragment {

    public TabMainFragment() {
        Log.v("TABMainFragment" , "onCreate");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("TABMainFragment" , "onCreate");
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public Object getPM() {
        return new TabMainPM("首页");
    }
}
