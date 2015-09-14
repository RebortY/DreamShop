package com.dream.main.infoview.jiexiao;

import com.dream.bean.goodinfo.QishulistEntity;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/9/14.
 * 揭晓列表
 */
public class JiexiaoItemPM implements ItemPresentationModel<QishulistEntity> {

    QishulistEntity entity ;

    //中奖用户头像
    private String url;
    private boolean circle = true;
    //揭晓时间
    private String showTime;
    //获奖者
    private String winner;


    @Override
    public void updateData(QishulistEntity qishulistEntity, ItemContext itemContext) {
            entity = qishulistEntity;
    }


}
