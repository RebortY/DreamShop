package com.dream.main.seach;

import android.view.View;

import com.dream.bean.SeachGood;

/**
 * Created by yangll on 15/9/12.
 */
public interface SeachView  {
    public void stopLoad();
    public void onItenClick(View view, SeachGood seachGood);
}
