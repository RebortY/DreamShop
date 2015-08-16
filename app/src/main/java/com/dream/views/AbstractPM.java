package com.dream.views;

import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

/**
 * Created by yangll on 15/8/15.
 */
public abstract class AbstractPM  implements HasPresentationModelChangeSupport{

    private PresentationModelChangeSupport changeSupport = null;
    public AbstractPM() {
        changeSupport = new PresentationModelChangeSupport(this);
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
