package com.dream.main;

import com.dream.bean.Category;

import org.robobinding.itempresentationmodel.ItemContext;

/**
 * Created by yangll on 15/9/7.
 */
public class NavigationItemPM implements org.robobinding.itempresentationmodel.ItemPresentationModel<Category> {


    Category category = null;

    @Override
    public void updateData(Category category, ItemContext itemContext) {
        this.category = category;
    }

    public String getValue() {
        return category.getName();
    }



}
