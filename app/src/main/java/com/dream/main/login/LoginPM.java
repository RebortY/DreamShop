package com.dream.main.login;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/24 20:12
 */
@PresentationModel
public class LoginPM extends AbstractPM {

    String userName;

    String userPsd;

    LoginView loginView;

    public LoginPM(LoginView loginViews){
        this.loginView = loginViews;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public void onClicks(ClickEvent event){

        loginView.setOnClickView(event.getView());
    }
}
