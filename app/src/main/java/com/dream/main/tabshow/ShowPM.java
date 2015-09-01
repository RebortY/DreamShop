package com.dream.main.tabshow;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class ShowPM  extends AbstractPM{

    private ShowView view;


    public ShowPM(ShowView view) {
        this.view = view;
    }


}
