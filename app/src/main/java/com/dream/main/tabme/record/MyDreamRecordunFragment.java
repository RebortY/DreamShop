package com.dream.main.tabme.record;


import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseFragment;
import com.dream.main.infoview.GoodInfoActivity;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 25:12
 * 我的元梦购已经结束的
 */

public class MyDreamRecordunFragment extends BaseFragment implements MyDreamRecordView {

    MyDreamRecordunFragmentPM fragmentPM;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_my_dreamrecordun;
    }

    @Override
    public Object initPM() {
        if (fragmentPM == null) {
            fragmentPM = new MyDreamRecordunFragmentPM(this);
        }
        return fragmentPM;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    @Override
    public void onClick(View view, Object info) {

        switch (view.getId()) {
            case R.id.tv_ckgd:
                MyDreamRecordUnInfo inFo = (MyDreamRecordUnInfo) info;

                Intent intent = new Intent(getActivity(), GoodInfoActivity.class);
                intent.putExtra(GoodInfoActivity.GOODID, String.valueOf(inFo.getShopid()));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void stopLoad(View view) {
        ((XListView) view).stopLoadMore();
    }
}