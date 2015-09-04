package com.dream.views;

import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

/**
 * Created by yangll on 15/8/15.
 */
public abstract class AbstractPM  implements HasPresentationModelChangeSupport{

    //下拉按钮出现时是否内容不移动
    private boolean pain = true;
    //使用下来按钮风格 目前只支持1
    private int type = 1;
    private int mintime = 1000;

    private PresentationModelChangeSupport changeSupport = null;
    public AbstractPM() {
        changeSupport = new PresentationModelChangeSupport(this);
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public boolean isPain() {
        return pain;
    }

    public int getType() {
        return type;
    }

    public int getMintime() {
        return mintime;
    }

    /**
     * 下拉刷新
     */
    public void refresh(MaterialPullRefreshEvent event) {
    }
}
