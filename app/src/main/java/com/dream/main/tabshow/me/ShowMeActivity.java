package com.dream.main.tabshow.me;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.base.BaseActivity;
import com.dream.main.infoview.showgood.ShowInfoActivity;
import com.dream.main.tabshow.CommentActivity;
import com.dream.main.tabshow.ShowView;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * @author yangll
 * 我自己的晒单列表
 */
public class ShowMeActivity extends BaseActivity implements ShowView{

    @Override
    public int getLayoutId() {
        return R.layout.showme;
    }

    @Override
    public Object initPM() {
        return new ShowMePM(this);
    }

    @Override
    public void onClick(View view , GoodForm good) {
        switch (view.getId()){
            case R.id.comment: //评论
                Intent intent  = new Intent( this, CommentActivity.class);
                intent.putExtra(CommentActivity.GOODID,good.getSd_id()+"");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void intentShowInfo(GoodForm good) {
        Intent intent = new Intent(this,ShowInfoActivity.class);
        intent.putExtra(ShowInfoActivity.GOODFORM,good);
        startActivity(intent);
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
    public boolean isme() {
        return true;
    }
}
