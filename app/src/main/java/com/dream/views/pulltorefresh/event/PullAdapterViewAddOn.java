package com.dream.views.pulltorefresh.event;

import android.widget.AdapterView;

import com.slib.pulltoviews.pulltorefresh.PullToRefreshAdapterViewBase;

import org.robobinding.widgetaddon.view.ViewAddOnForView;

/**
 * Created by yangll on 15/9/4.
 */
public class PullAdapterViewAddOn extends ViewAddOnForView {

    AdapterView<?> adapterView = null;
    private OnItemSelectedListeners onItemSelectedListeners;
    private OnItemClickListeners onItemClickListeners;

    public PullAdapterViewAddOn(PullToRefreshAdapterViewBase view) {
        super(view);

        adapterView = (AdapterView)view.getRefreshableView();
    }

    public void addOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        ensureOnItemSelectedListenersInitialized();
        onItemSelectedListeners.addListener(onItemSelectedListener);
    }

    public void addOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        ensureOnItemClickListenersInitialized();
        onItemClickListeners.addListener(onItemClickListener);
    }

    private void ensureOnItemSelectedListenersInitialized() {
        if (onItemSelectedListeners == null) {
            onItemSelectedListeners = new OnItemSelectedListeners();
            adapterView.setOnItemSelectedListener(onItemSelectedListeners);
        }
    }

    private void ensureOnItemClickListenersInitialized() {
        if (onItemClickListeners == null) {
            onItemClickListeners = new OnItemClickListeners();
            adapterView.setOnItemClickListener(onItemClickListeners);
        }

    }
}
