package com.dream.main.tabme;

import com.dream.main.base.BaseActView;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/29 17:06
 */

@PresentationModel

public class UserInfoPM extends AbstractPM {

    BaseActView meFragmentView;

    public UserInfoPM(BaseActView meFragmentViews){
        this.meFragmentView = meFragmentViews;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickView(event.getView());
    }
}
