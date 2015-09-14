package com.dream.main.infoview.jiexiao;

import com.dream.bean.goodinfo.QishulistEntity;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/14.
 */
@PresentationModel
public class JiexiaoPM  implements HasPresentationModelChangeSupport{

    PresentationModelChangeSupport changeSupport = null;
    private List<QishulistEntity> data = new ArrayList<>();

    public JiexiaoPM() {
        changeSupport = new PresentationModelChangeSupport(this);
    }




    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
