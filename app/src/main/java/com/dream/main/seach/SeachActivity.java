package com.dream.main.seach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.dream.R;
import com.dream.bean.SeachGood;
import com.dream.main.base.BaseActivity;
import com.dream.main.infoview.GoodInfoActivity;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshBase;
import com.slib.pulltoviews.pulltorefresh.PullToRefreshScrollView;

import butterknife.Bind;

/**
 * Created by yangll on 15/9/9.
 */
public class SeachActivity extends BaseActivity implements SeachView {

    static final String SEACH_SET_TAG = "seach_set_data";
    static final String SEACH_ADD_TAG = "seach_add_data";
    @Bind(R.id.seach_scrollview)
    PullToRefreshScrollView seachScrollview;
    private SeachPM pm = null;

    @Override
    public int getLayoutId() {
        return R.layout.seachlayout;
    }

    @Override
    public Object initPM() {
        pm = new SeachPM(this);
        return pm;
    }

    @Override
    public void onItenClick(View view, SeachGood info) {
        SeachGood inFo = (SeachGood) info;

        Intent intent = new Intent(this, GoodInfoActivity.class);
        intent.putExtra(GoodInfoActivity.GOODID, String.valueOf(inFo.getId()));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seachScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pm.onGridLoad(null);
            }
        });
    }

    @Override
    public void stopLoad() {
        seachScrollview.onRefreshComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
