package com.dream.main.tabme.prize;

import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.main.base.StopRefreshView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 21:59
 * 我获得的奖品
 */
public class MyPrizeAct extends BaseActivity implements StopRefreshView {

    MyPrizePM myPrizePM;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_prize;
    }

    @Override
    public Object initPM() {
        if(myPrizePM == null){
            myPrizePM = new MyPrizePM(this);
        }
        return myPrizePM;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh)view).refreshComplete();
    }

    @Override
    public void stopLoad(View view) {
        ((XListView)view).stopLoadMore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }
}
