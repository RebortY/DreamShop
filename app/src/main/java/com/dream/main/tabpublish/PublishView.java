package com.dream.main.tabpublish;

import android.view.View;

import com.dream.bean.Good;

/**
 * Created by yangll on 15/9/4.
 */
public interface PublishView {

    void stopRefresh(View view);
    void intentInfoView(Good good);
    void stopLoad();
}
