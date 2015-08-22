package com.dream.main.tabmain;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.slib.viewpagerindicator.TabPageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangll on 15/8/16.
 * <p/>
 * Fragment 增加一些Fragment 自身的处理
 * 对应的 PM 处理网络，逻辑，数据的 处理
 */
public class TabMainFragment extends AbstractTabFragment {

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.pager_indicator)
    TabPageIndicator pagerIndicator;

    public TabMainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        pager.setAdapter(new ViewPageAdapter());
        pagerIndicator.setViewPager(pager);
        return rootView;
    }

    class ViewPageAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
