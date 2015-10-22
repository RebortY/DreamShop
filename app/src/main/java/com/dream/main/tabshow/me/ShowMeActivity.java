package com.dream.main.tabshow.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.base.BaseActivity;
import com.dream.main.infoview.showgood.ShowInfoActivity;
import com.dream.main.tabshow.CommentActivity;
import com.dream.main.tabshow.ShowView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshBase;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yangll
 *         我自己的晒单列表
 */
public class ShowMeActivity extends BaseActivity implements ShowView {

    @Bind(R.id.pullscrollview)
    PullToRefreshScrollView pullscrollview;

    ShowMePM pm = null;
    @Override
    public int getLayoutId() {
        return R.layout.showme;
    }

    @Override
    public Object initPM() {
        pm = new ShowMePM(this);
        return pm;
    }

    @Override
    public void onClick(View view, GoodForm good) {
        switch (view.getId()) {
            case R.id.comment: //评论
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(CommentActivity.GOODID, good.getSd_id() + "");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void intentShowInfo(GoodForm good) {
        Intent intent = new Intent(this, ShowInfoActivity.class);
        intent.putExtra(ShowInfoActivity.GOODFORM, good);
        startActivity(intent);
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void stopLoad() {
        pullscrollview.onRefreshComplete();
    }

    @Override
    public boolean isme() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        pullscrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pm.onload();
            }
        });
        pullscrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    @Override
    public void changeListHeight() {

    }
}
