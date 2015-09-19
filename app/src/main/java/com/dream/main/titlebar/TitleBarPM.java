package com.dream.main.titlebar;

import android.app.Activity;

import com.dream.R;
import com.dream.main.DreamApplication;

import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.view.ClickEvent;

/**
 * @author yangll
 */
public abstract class TitleBarPM  implements HasPresentationModelChangeSupport{

    private boolean leftVisibility = true;
    private boolean rightVisibility = false;
    private String titleBar = DreamApplication.getApp().getString(R.string.app_name);
    private int titleColor = DreamApplication.getApp().getResources().getColor(R.color.white);

    PresentationModelChangeSupport changeSupport;
    public TitleBarPM() {
        changeSupport =  new PresentationModelChangeSupport(this);
    }

    public boolean isLeftVisibility() {
        return leftVisibility;
    }

    public void setLeftVisibility(boolean leftVisibility) {
        this.leftVisibility = leftVisibility;
        changeSupport.firePropertyChange("leftVisibility");
    }

    public boolean isRightVisibility() {
        return rightVisibility;
    }

    public void setRightVisibility(boolean rightVisibility) {
        this.rightVisibility = rightVisibility;
        changeSupport.firePropertyChange("rightVisibility");
    }

    public String getTitleBar() {
        return titleBar;
    }

    public void setTitleBar(String title) {
        this.titleBar = title;
        changeSupport.firePropertyChange("titleBar");
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        changeSupport.firePropertyChange("titleColor");
    }

    public void onLeftClick(ClickEvent event){
       Activity ctx =   (Activity)event.getView().getContext();
        ctx.finish();
    }

    public void onRightClick(ClickEvent event){

    }


    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public void pmRefresh(String params){
        changeSupport.firePropertyChange(params);
    }
}
