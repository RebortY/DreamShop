package com.dream.main.tabme;

import android.view.View;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/20 09:55
 */
public interface MeFragmentView {

    void setOnClickView(View view);

    /**
     *
     * @param type  0未登录  1登录
     */
    public void onClickView(int type);
}
