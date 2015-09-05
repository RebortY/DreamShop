package com.dream.main.tabshow;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yangll on 15/8/31.
 */
public interface ShowView {
    void onClick(View view);
    void stopRefresh(View view);
    void stopLoad(View view);
    LayoutInflater getLayoutInflater();
}
