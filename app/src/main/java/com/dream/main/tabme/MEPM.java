package com.dream.main.tabme;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dream.R;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import com.dream.views.layout.LayoutItem;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM{

    MeFragmentView meFragmentView;

    public MEPM(MeFragmentView meView) {
        this.meFragmentView = meView;
    }

    private String userName = "13466452759";//用户名


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickId(event.getView().getId());
    }

}
