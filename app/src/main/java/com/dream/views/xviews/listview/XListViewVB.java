package com.dream.views.xviews.listview;

import com.slib.pulltoviews.xviews.widget.XListView;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

/**
 * Created by yangll on 15/9/5.
 */
@ViewBinding(simpleOneWayProperties = {"pullLoadEnable"})
public class XListViewVB extends org.robobinding.customviewbinding.CustomViewBinding<XListView> {
    @Override
    public void mapBindingAttributes(BindingAttributeMappings<XListView> mappings) {
        mappings.mapEvent(XListViewOnLoadAttribute.class,"onLoad");
    }
}
