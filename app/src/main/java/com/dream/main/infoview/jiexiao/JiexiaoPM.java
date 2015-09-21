package com.dream.main.infoview.jiexiao;

import com.dream.bean.goodinfo.QishulistEntity;
import com.dream.main.titlebar.TitleBarPM;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/14.
 */
@PresentationModel
public class JiexiaoPM extends TitleBarPM{

    private List<QishulistEntity> data = new ArrayList<>();

    private boolean  loadEnable = false;
    public JiexiaoPM() {
        setTitleBar("往期揭晓");
    }


    public void setData(List<QishulistEntity> listdata) {
        if (listdata == null) return;
        data.clear();
        data.addAll(listdata);
    }

    @ItemPresentationModel(value = JiexiaoItemPM.class)
    public List<QishulistEntity> getData() {
        return data;
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }


}
