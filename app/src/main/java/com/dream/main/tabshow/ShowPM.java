package com.dream.main.tabshow;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class ShowPM  extends AbstractPM{
    public ShowPM(String title) {
        this.title = title;
    }

    private String title = "首页";

    public String getTitle() {
        return title;
    }

}
