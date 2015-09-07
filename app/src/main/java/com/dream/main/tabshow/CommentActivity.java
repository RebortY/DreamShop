package com.dream.main.tabshow;

import android.os.Bundle;
import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActivity;
import com.dream.views.uitra.MaterialPullRefresh;

/**
 * Created by yangll on 15/9/6.
 * 评论页面
 */
public class CommentActivity extends BaseActivity implements CommentView {

    //晒单中的商品ID
    public static final String GOODID = "SHOW_GOODID";
    private String showId;
    private CommentPM pm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showId = getIntent().getStringExtra(GOODID);
        pm.setShowId(showId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.comment;
    }

    @Override
    public Object initPM() {
         pm = new CommentPM(this);
        return pm;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh)view).refreshComplete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pm.unregister();
    }
}
