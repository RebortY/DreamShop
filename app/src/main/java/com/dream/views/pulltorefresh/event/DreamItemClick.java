package com.dream.views.pulltorefresh.event;

import android.view.View;
import android.widget.AdapterView;

import org.robobinding.widget.view.ClickEvent;

/**
 * Created by yangll on 15/9/4.
 */
public class DreamItemClick extends ClickEvent {

    private AdapterView<?> parent;
    private int position;
    private long id;

    public DreamItemClick(AdapterView<?> parent , View view , int position , long id) {
        super(view);
    }

    public int getPosition() {
        return this.position;
    }

    public long getId() {
        return this.id;
    }

    public AdapterView<?> getParent() {
        return this.parent;
    }

}
