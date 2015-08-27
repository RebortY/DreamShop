package com.dream.views.uitra;

import org.robobinding.annotation.ViewBinding;
import org.robobinding.customviewbinding.CustomViewBinding;
import org.robobinding.viewbinding.BindingAttributeMappings;

/**
 * Created by yangll on 15/8/27.
 */
@ViewBinding(simpleOneWayProperties = {"type","mintime","pincontent"})
public class MaterialPullRefreshVB extends CustomViewBinding<MaterialPullRefresh> {

    @Override
    public void mapBindingAttributes(BindingAttributeMappings<MaterialPullRefresh> mappings) {
            mappings.mapEvent(MaterialPullAttribute.class, "onRefreshBegin");
    }

}
