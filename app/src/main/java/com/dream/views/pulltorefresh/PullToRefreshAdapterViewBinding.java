package com.dream.views.pulltorefresh;

import com.slib.pulltoviews.pulltorefresh.PullToRefreshAdapterViewBase;

import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_LAYOUT;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.ITEM_MAPPING;
import static org.robobinding.widget.adapterview.AbstractAdaptedDataSetAttributes.SOURCE;

/**
 * Created by yangll on 15/9/3.
 *
 */
public class PullToRefreshAdapterViewBinding extends CustomViewBinding<PullToRefreshAdapterViewBase> {

    @Override
    public void mapBindingAttributes(BindingAttributeMappings<PullToRefreshAdapterViewBase> bindingAttributeMappings) {

        bindingAttributeMappings.mapTwoWayProperty(SelectedItemPositionAttribute.class ,"selectedItemPosition" );

        bindingAttributeMappings.mapEvent(OnItemClickAttribute.class, "onItemClick");
        bindingAttributeMappings.mapEvent(OnItemSelectedAttribute.class, "onItemSelected");

        bindingAttributeMappings.mapGroupedAttribute(PullToRefreshAdapterAttribute.class, SOURCE, ITEM_LAYOUT, ITEM_MAPPING);

    }


}
