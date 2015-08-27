package com.dream.views.uitra;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;
import org.robobinding.widgetaddon.ViewAddOn;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by yangll on 15/8/27.
 */
public class MaterialPullAttribute implements EventViewAttribute<MaterialPullRefresh, ViewAddOn> {

    @Override
    public void bind(ViewAddOn viewAddOn, final Command command, final MaterialPullRefresh materialPullRefresh) {
        materialPullRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                MaterialPullRefreshEvent event = new MaterialPullRefreshEvent(materialPullRefresh);
                command.invoke(event);
            }
        });
    }

    @Override
    public Class<?> getEventType() {
        return MaterialPullRefreshEvent.class;
    }
}
