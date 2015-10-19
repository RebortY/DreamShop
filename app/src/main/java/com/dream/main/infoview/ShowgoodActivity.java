package com.dream.main.infoview;

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
 */
public class ShowgoodActivity extends BaseActivity implements ShowView {

    public static final String SID = "sid";
    private String sid ;

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
        return new ShowgoodPM(this,sid );
    }

    @Override
    public void onClick(View view , GoodForm good) {
        switch (view.getId()){
            case R.id.comment: //评论
                Intent intent  = new Intent(this, CommentActivity.class);
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
        return false;
    }


}
