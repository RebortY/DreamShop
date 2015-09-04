package com.dream.views.pulltorefresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.dream.views.pulltorefresh.event.DreamItemClick;
import com.dream.views.pulltorefresh.event.PullAdapterViewAddOn;
import com.slib.pulltoviews.PullToRefreshAdapterViewBase;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;

/**
 * Created by yangll on 15/9/4.
 */
public class OnItemClickAttribute implements EventViewAttribute<PullToRefreshAdapterViewBase , PullAdapterViewAddOn> {
    @Override
    public void bind(PullAdapterViewAddOn pullAdapterViewAddOn, final Command command, PullToRefreshAdapterViewBase pullToRefreshAdapterViewBase) {
        pullAdapterViewAddOn.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DreamItemClick clickEvent = new DreamItemClick(parent , view , position , id );
                command.invoke(clickEvent);
            }
        });
    }

    @Override
    public Class<DreamItemClick> getEventType() {
        return DreamItemClick.class;
    }
}
