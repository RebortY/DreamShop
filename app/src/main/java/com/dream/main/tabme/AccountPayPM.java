package com.dream.main.tabme;

import com.dream.main.base.BaseActView;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/27 18:58
 */
@PresentationModel
public class AccountPayPM extends AbstractPM {

    BaseActView meFragmentView;

    String balance;

    public AccountPayPM(BaseActView baseActViews){

        this.meFragmentView = baseActViews;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void onClicks(ClickEvent event){

        meFragmentView.setOnClickView(event.getView());
    }
}
