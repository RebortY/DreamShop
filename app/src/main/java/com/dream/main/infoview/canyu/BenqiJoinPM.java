package com.dream.main.infoview.canyu;

import com.dream.bean.goodinfo.RecordsEntity;
import com.dream.main.titlebar.TitleBarPM;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/15.
 */
@PresentationModel
public class BenqiJoinPM extends TitleBarPM{

    private List<RecordsEntity> data = new ArrayList<>();

    private boolean  loadEnable = false;

    public BenqiJoinPM() {
        setTitleBar("本期参与");
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

    public boolean isLoadEnable() {
        return loadEnable;
    }


}
