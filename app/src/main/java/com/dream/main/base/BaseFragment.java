package com.dream.main.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 21:00
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getlayoutId(), null);
        return view;
    }

    public  abstract  int getlayoutId();
}
