package com.dream.main.tabmain;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.views.imageview.DreamImageView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by yangll on 15/8/16.
 * <p/>
 * Fragment 增加一些Fragment 自身的处理
 * 对应的 PM 处理网络，逻辑，数据的 处理
 */
public class TabMainFragment extends AbstractTabFragment implements TabMainView {

    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.pager_indicator)
    CirclePageIndicator pagerIndicator;
    ViewPageAdapter adapter = null;
    TabMainPM tabMainPM = null;

    public TabMainFragment() {
        adapter = new ViewPageAdapter();
        tabMainPM = new TabMainPM(this);
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
        return tabMainPM;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        pager.setAdapter(adapter);
        pagerIndicator.setViewPager(pager);
        return rootView;
    }

    //轮播图适配器
    @Override
    public void setCarouselAdapter(List<Carousel> datas) {
        pager.setOffscreenPageLimit(datas.size());
        adapter.setData(datas);
        pagerIndicator.notifyDataSetChanged();
    }

    /**
     * 处理页面显示商品的类型
     *
     * @param bean
     * @param view
     */
    @Override
    public void handlGoodsView(AbstractBean bean, View view) {

    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh)view).refreshComplete();
    }

    @Override
    public void showToast(String str) {
        if (getActivity() != null)
            Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    class ViewPageAdapter extends PagerAdapter {

        ArrayList<DreamImageView> data = new ArrayList<DreamImageView>();

        public ViewPageAdapter() {
        }

        public void setData(List<Carousel> datas) {
            if (datas == null) return;
            data.clear();
            for (Carousel carousel : datas) {
                DreamImageView imageView = new DreamImageView(getActivity());
                imageView.setImageURI(Uri.parse(carousel.getSrc()));
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.top_bar_carousel)));
                data.add(imageView);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(data.get(position));
            return data.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        tabMainPM.unregister();
    }
}
