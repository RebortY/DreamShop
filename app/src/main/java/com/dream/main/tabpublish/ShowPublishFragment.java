package com.dream.main.tabpublish;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.AbstractTabFragment;
import com.dream.main.infoview.GoodInfoActivity;
import com.dream.views.uitra.MaterialPullRefresh;

/**
 * Created by yangll on 15/8/16.
 * 揭晓
 */
public class ShowPublishFragment extends AbstractTabFragment implements PublishView {

    PublishPM pm = null;

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
        Intent intent = new Intent(getActivity() , GoodInfoActivity.class);
        intent.putExtra(GoodInfoActivity.GOODID,good.getSid());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pm.unregister();
    }

}
