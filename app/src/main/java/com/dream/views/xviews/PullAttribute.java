package com.dream.views.xviews;

import com.slib.pulltoviews.xviews.widget.IXViewListener;
import com.slib.pulltoviews.xviews.widget.XView;

import org.robobinding.attribute.Command;
import org.robobinding.viewattribute.event.EventViewAttribute;
import org.robobinding.widgetaddon.ViewAddOn;

/**
 * Created by yangll on 15/9/5.
 */
public class PullAttribute <T extends XView> implements EventViewAttribute<T, ViewAddOn> {

    @Override
    public void bind(ViewAddOn viewAddOn, final Command command, final T t) {
            t.setIXViewListener(new IXViewListener() {
                @Override
                public void onRefresh() {
                    XPullEvent event = new XPullEvent(t.getView());
                    command.invoke(event);
                }

                @Override
                public void onLoadMore() {

                }
            });
    }

    @Override
    public Class<XPullEvent> getEventType() {
        return XPullEvent.class;
    }
}
