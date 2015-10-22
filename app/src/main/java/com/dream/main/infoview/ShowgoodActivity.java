package com.dream.main.infoview;

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
 */
public class ShowgoodActivity extends BaseActivity implements ShowView {

    public static final String SID = "sid";
    @Bind(R.id.pullscrollview)
    PullToRefreshScrollView pullscrollview;
    private String sid;

    ShowgoodPM pm = null;

    @Override
    public void setAttIntent(Intent intent) {
        this.sid = intent.getStringExtra(SID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.showgood;
    }

    @Override
    public Object initPM() {
        pm = new ShowgoodPM(this, sid);
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
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
    }

    @Override
    public void changeListHeight() {

    }
}
