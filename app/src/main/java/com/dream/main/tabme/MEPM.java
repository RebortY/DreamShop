package com.dream.main.tabme;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM{

    public MEPM(String title) {
        this.title = title;
    }

    private String title = "首页";

    public String getTitle() {
        return title;
    }


}
