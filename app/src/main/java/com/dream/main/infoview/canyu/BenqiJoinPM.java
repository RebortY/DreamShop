package com.dream.main.infoview.canyu;

import com.dream.bean.goodinfo.RecordsEntity;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/15.
 */
@PresentationModel
public class BenqiJoinPM implements HasPresentationModelChangeSupport {

    PresentationModelChangeSupport changeSupport = null;
    private List<RecordsEntity> data = new ArrayList<>();

    private boolean  loadEnable = false;

    public BenqiJoinPM() {
        changeSupport = new PresentationModelChangeSupport(this);
    }

    public void setData(List<RecordsEntity> listdata) {
        if (listdata == null) return;
        data.clear();
        data.addAll(listdata);
    }

    @ItemPresentationModel(value = BeanqiJoinItemPm.class)
    public List<RecordsEntity> getData() {
        return data;
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }


}
