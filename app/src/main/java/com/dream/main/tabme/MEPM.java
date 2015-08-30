package com.dream.main.tabme;

import com.dream.main.base.BaseActView;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM{

    BaseActView meFragmentView;

    public MEPM(BaseActView meView) {
        this.meFragmentView = meView;
    }

    private String userName;//用户名


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickView(event.getView());
    }

}
