package com.dream.views.pulltorefresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.dream.views.pulltorefresh.event.DreamItemClick;
import com.dream.views.pulltorefresh.event.PullAdapterViewAddOn;
import com.slib.pulltoviews.PullToRefreshAdapterViewBase;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;
import org.robobinding.widget.adapterview.ItemClickEvent;

/**
 * Created by yangll on 15/9/4.
 */
public class OnItemSelectedAttribute implements EventViewAttribute<PullToRefreshAdapterViewBase<?> , PullAdapterViewAddOn> {

    @Override
    public void bind(PullAdapterViewAddOn pullAdapterViewAddOn, final Command command, PullToRefreshAdapterViewBase<?> absListViewPullToRefreshAdapterViewBase) {
        pullAdapterViewAddOn.addOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DreamItemClick itemClickEvent = new DreamItemClick(parent, view, position, id);
                command.invoke(itemClickEvent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                DreamItemClick itemClickEvent = new DreamItemClick(parent, null, AdapterView.INVALID_POSITION, 0);
                command.invoke(itemClickEvent);
            }
        });
    }

    @Override
    public Class<DreamItemClick> getEventType() {
        return DreamItemClick.class;
    }
}
