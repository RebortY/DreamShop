package com.dream.main.tabme.address;

import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/5 14:15
 */
@PresentationModel
public class AddressEditPM extends TitleBarPM {

    BaseActView baseActView;

    AddressEditPM(BaseActView baseActViews){
        this.baseActView = baseActViews;
    }

    public void onClicks(ClickEvent event) {

        baseActView.setOnClickView(event.getView());

    }

    @Override
    public String getTitleBar() {
        return "编辑收获地址";
    }
}
