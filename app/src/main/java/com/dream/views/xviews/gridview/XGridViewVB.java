package com.dream.views.xviews.gridview;

import com.slib.pulltoviews.xviews.widget.XGridView;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

/**
 * Created by yangll on 15/9/5.
 */
@ViewBinding(simpleOneWayProperties = {"pullLoadEnable"})
public class XGridViewVB extends org.robobinding.customviewbinding.CustomViewBinding<XGridView> {
    @Override
    public void mapBindingAttributes(BindingAttributeMappings<XGridView> mappings) {
        mappings.mapEvent(XGridViewOnLoadAttribute.class,"onLoad");
    }
}
