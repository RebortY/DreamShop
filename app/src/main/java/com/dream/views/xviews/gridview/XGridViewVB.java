package com.dream.views.xviews.gridview;

import com.paging.gridview.PagingGridView;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

/**
 * Created by yangll on 15/9/5.
 */
@ViewBinding(simpleOneWayProperties = {"hasMoreItems"})
public class XGridViewVB extends org.robobinding.customviewbinding.CustomViewBinding<PagingGridView> {
    @Override
    public void mapBindingAttributes(BindingAttributeMappings<PagingGridView> mappings) {
        mappings.mapEvent(XGridViewOnLoadAttribute.class,"onLoad");
    }
}
