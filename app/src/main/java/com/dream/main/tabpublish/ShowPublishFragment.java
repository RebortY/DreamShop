package com.dream.main.tabpublish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.AbstractTabFragment;
import com.dream.main.infoview.GoodInfoActivity;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshBase;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangll on 15/8/16.
 * 揭晓
 */
public class ShowPublishFragment extends AbstractTabFragment implements PublishView {

    PublishPM pm = null;
    @Bind(R.id.pullscrollview)
    PullToRefreshScrollView pullToRefreshScrollView;


    public ShowPublishFragment() {
        pm = new PublishPM(this);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_publish;
    }

    @Override
    public Object getPM() {
        return pm;
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }


    @Override
    public void intentInfoView(Good good) {
        Intent intent = new Intent(getActivity(), GoodInfoActivity.class);
        intent.putExtra(GoodInfoActivity.GOODID, good.getId());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pm.unregister();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pm.refresh(null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pm.onload();
            }
        });
        return rootView;
    }

    @Override
    public void stopLoad() {
        pullToRefreshScrollView.onRefreshComplete();
    }
}
