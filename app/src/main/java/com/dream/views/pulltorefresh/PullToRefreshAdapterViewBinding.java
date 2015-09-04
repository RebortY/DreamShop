package com.dream.views.pulltorefresh;

import android.widget.AbsListView;

import com.slib.pulltoviews.PullToRefreshAdapterViewBase;

import org.robobinding.viewbinding.BindingAttributeMappings;
import org.robobinding.viewbinding.ViewBinding;

import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_LAYOUT;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_MAPPING;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.SOURCE;

/**
 * Created by yangll on 15/9/3.
 *
 */
public class PullToRefreshAdapterViewBinding<T extends PullToRefreshAdapterViewBase<? extends AbsListView>> implements ViewBinding<PullToRefreshAdapterViewBase<? extends AbsListView>> {

    @Override
    public void mapBindingAttributes(BindingAttributeMappings<PullToRefreshAdapterViewBase<? extends AbsListView>> bindingAttributeMappings) {

        bindingAttributeMappings.mapTwoWayProperty(SelectedItemPositionAttribute.class ,"selectedItemPosition" );

        bindingAttributeMappings.mapEvent(OnItemClickAttribute.class, "onItemClick");
        bindingAttributeMappings.mapEvent(OnItemSelectedAttribute.class, "onItemSelected");

        bindingAttributeMappings.mapGroupedAttribute(PullToRefreshAdapterAttribute.class, SOURCE, ITEM_LAYOUT, ITEM_MAPPING);

    }
}
