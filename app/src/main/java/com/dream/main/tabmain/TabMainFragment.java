package com.dream.main.tabmain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.AbstractTabFragment;
import com.dream.main.infoview.GoodInfoActivity;
import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.main.webview.WebViewActivity;
import com.dream.views.imageview.DreamImageView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.facebook.drawee.drawable.ScalingUtils;
import com.slib.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by yangll on 15/8/16.
 * <p>
 * Fragment 增加一些Fragment 自身的处理
 * 对应的 PM 处理网络，逻辑，数据的 处理
 */
public class TabMainFragment extends AbstractTabFragment implements TabMainView {

    @Bind(R.id.pager)
    AutoScrollViewPager pager;
    @Bind(R.id.pager_indicator)
    CirclePageIndicator pagerIndicator;
    @Bind(R.id.main_scroll)
    ScrollView scrollView;

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
        startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        pager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        startAutoScroll();
    }

    private void startAutoScroll() {
        pager.startAutoScroll(3000);
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
    public void gowebView(String url) {
        Intent intent = new Intent(getActivity() , WebViewActivity.class);
        intent.putExtra(WebViewActivity.URI,url);
        startActivity(intent);
    }

    //跳转到 商品详情
    @Override
    public void goGoodInfo(Good goodId) {
        Intent intent = new Intent(getActivity(), GoodInfoActivity.class);
        intent.putExtra(GoodInfoActivity.GOODID, goodId.getId());
        startActivity(intent);
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void showToast(int strId) {
        if (getActivity() != null)
            Toast.makeText(getActivity(), getResources().getString(strId), Toast.LENGTH_SHORT).show();
    }

    public void startIntent(String url) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URI, url);
        startActivity(intent);
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
                imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
                imageView.setTag(carousel);
                imageView.setOnClickListener((view) -> {
                    String url = ((Carousel) view.getTag()).getUrl();
                    startIntent(url);
                });
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

    public void scrollTop() {
        if (scrollView != null)
            scrollView.smoothScrollTo(0,0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        tabMainPM.unregister();
    }
}
