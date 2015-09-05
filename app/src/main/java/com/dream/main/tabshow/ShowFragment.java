package com.dream.main.tabshow;

import android.view.LayoutInflater;
import android.view.View;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * Created by yangll on 15/8/16.
 */
public class ShowFragment extends AbstractTabFragment implements ShowView {

    @Override
    public int getlayoutId() {
        return R.layout.fragment_show;
    }

    @Override
    public Object getPM() {
        return new ShowPM(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.praise://点赞
                ToastUtil.show("点赞");
                break;
            case R.id.comment: //评论
                ToastUtil.show("评论");
                break;
            case R.id.try_again: //我也试试
                ToastUtil.show("我也试试");
                break;
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return getLayoutInflater(getArguments());
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
    public AbstractTabFragment getCurFragment() {
        return this;
    }
}
