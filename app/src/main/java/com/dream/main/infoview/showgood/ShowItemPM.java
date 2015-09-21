package com.dream.main.infoview.showgood;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * @author yangll
 */
public class ShowItemPM implements ItemPresentationModel<String> {


    private String url = "res://R.drawable.good_default";

    @Override
    public void updateData(String s, ItemContext itemContext) {
        url = s;
    }

    public String getUrl() {
        return url;
    }

}
