package com.dream.main.seach;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/9/10.
 */
public class SeachEmptyItemPM implements ItemPresentationModel<String> {

    private String value;

    @Override
    public void updateData(String s, ItemContext itemContext) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
