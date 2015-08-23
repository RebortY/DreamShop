package com.dream.main.tabme;

import com.dream.R;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import com.dream.views.layout.LayoutItem;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class MEPM extends AbstractPM{

    public MEPM(String title) {
        this.title = title;
    }

    private String title = "首页";

    private String userName = "13466452759";//用户名


    public String getTitle() {
        return title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
