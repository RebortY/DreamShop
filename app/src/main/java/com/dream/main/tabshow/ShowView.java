package com.dream.main.tabshow;

import android.view.LayoutInflater;
import android.view.View;

import com.dream.bean.GoodForm;

/**
 * Created by yangll on 15/8/31.
 */
public interface ShowView {
    void onClick(View view , GoodForm form);
    void stopRefresh(View view);
    void stopLoad(View view);
    LayoutInflater getLayoutInflater();
    public void intentShowInfo(GoodForm good);
    public boolean isme();//是否是显示我的晒单
}
