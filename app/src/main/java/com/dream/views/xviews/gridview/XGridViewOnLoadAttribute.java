package com.dream.views.xviews.gridview;

import com.dream.views.xviews.XLoadEvent;
import com.paging.gridview.PagingGridView;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;
import org.robobinding.widgetaddon.ViewAddOn;

/**
 * Created by yangll on 15/9/5.
 */
public class XGridViewOnLoadAttribute implements EventViewAttribute<PagingGridView, ViewAddOn> {

    @Override
    public void bind(ViewAddOn viewAddOn, Command command, PagingGridView pagingGridView) {
        pagingGridView.setPagingableListener(()->{
            XLoadEvent event = new XLoadEvent(pagingGridView);
            command.invoke(event);
        });
    }

    @Override
    public Class<?> getEventType() {
        return XLoadEvent.class;
    }
}
