package com.dream.views.pulltorefresh.event;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.slib.pulltoviews.PullToRefreshAdapterViewBase;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;
import org.robobinding.widgetaddon.adapterview.AdapterViewAddOn;

/**
 * Created by yangll on 15/9/4.
 */
public abstract class PullEventViewAttributeForAdapterView  implements EventViewAttribute<PullToRefreshAdapterViewBase<AbsListView>, PullAdapterViewAddOn> {

}
