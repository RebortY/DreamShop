package com.dream.main.tabme.prize;

import android.view.View;

import com.dream.bean.MyPrizeInfo;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/23 17:59
 */
public interface MyPrizeView {

    void onClick(View view , MyPrizeInfo myPrizeInfo);
    void stopRefresh(View view);
    void stopLoad(View view);
}
