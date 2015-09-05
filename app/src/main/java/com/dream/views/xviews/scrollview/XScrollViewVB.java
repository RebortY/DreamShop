package com.dream.views.xviews.scrollview;

import com.slib.pulltoviews.xviews.widget.XScrollView;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

/**
 * Created by yangll on 15/9/5.
 */
@ViewBinding(simpleOneWayProperties = {"pullLoadEnable"})
public class XScrollViewVB extends org.robobinding.customviewbinding.CustomViewBinding<XScrollView> {
    @Override
    public void mapBindingAttributes(BindingAttributeMappings<XScrollView> mappings) {
        mappings.mapEvent(XScrollViewOnLoadAttribute.class,"onLoad");
    }
}
