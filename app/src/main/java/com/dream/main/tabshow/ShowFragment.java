package com.dream.main.tabshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.AbstractTabFragment;
import com.dream.main.infoview.showgood.ShowInfoActivity;
import com.dream.util.DreamUtils;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshBase;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangll on 15/8/16.
 * 主页的晒单列表
 */
public class ShowFragment extends AbstractTabFragment implements ShowView {

    @Bind(R.id.pullscrollview)
    PullToRefreshScrollView pullscrollview;

    ShowPM pm = null;
    @Bind(R.id.list)
    ListView list;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_show;
    }

    @Override
    public Object getPM() {
        pm = new ShowPM(this);
        return pm;
    }

    @Override
    public void onClick(View view, GoodForm good) {
        switch (view.getId()) {
            case R.id.comment: //评论
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra(CommentActivity.GOODID, good.getSd_id() + "");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void intentShowInfo(GoodForm good) {
        Intent intent = new Intent(getActivity(), ShowInfoActivity.class);
        intent.putExtra(ShowInfoActivity.GOODFORM, good);
        startActivity(intent);
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return getLayoutInflater(getArguments());
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void stopLoad() {
        if(pullscrollview != null){
            pullscrollview.onRefreshComplete();
        }
    }

    @Override
    public boolean isme() {
        return false;
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        pullscrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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
    public void changeListHeight() {
        DreamUtils.setListViewHeightBasedOnChildren(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
