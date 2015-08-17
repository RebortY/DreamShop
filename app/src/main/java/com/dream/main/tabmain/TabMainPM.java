package com.dream.main.tabmain;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/15.
 */
@PresentationModel
public class TabMainPM extends AbstractPM{

    public TabMainPM(String title) {
        this.title = title;
    }

    private String title = "首页";

    public String getTitle() {
        return title;
    }

}
