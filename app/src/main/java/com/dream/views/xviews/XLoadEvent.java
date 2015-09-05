package com.dream.views.xviews;

import android.view.View;

/**
 * Created by yangll on 15/9/5.
 */
public class XLoadEvent {
    View view = null;
    public XLoadEvent(View view ) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
