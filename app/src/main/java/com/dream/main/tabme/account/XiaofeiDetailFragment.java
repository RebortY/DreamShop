package com.dream.main.tabme.account;

import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseFragment;
import com.dream.main.base.StopRefreshView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/13 16:19
 */
public class XiaofeiDetailFragment extends BaseFragment implements StopRefreshView {


    XiaofeiDetailPM detailPM;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_chongzhi_detail;
    }

    @Override
    public Object initPM() {
        if (detailPM == null) {
            detailPM = new XiaofeiDetailPM(this);
        }
        return detailPM;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
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
