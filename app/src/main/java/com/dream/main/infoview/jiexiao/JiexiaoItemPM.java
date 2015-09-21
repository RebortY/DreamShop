package com.dream.main.infoview.jiexiao;

import com.dream.bean.goodinfo.QishulistEntity;
import com.dream.bean.goodinfo.User;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/9/14.
 * 揭晓列表
 */
public class JiexiaoItemPM implements ItemPresentationModel<QishulistEntity> {

    QishulistEntity entity ;

    //揭晓事件
    private String jiexiao_time;
    //中奖用户头像
    private String url = "file://drawable/R.drawable.default_head";
    private boolean circle = true;
    //获奖者
    private String winner;
    //幸运号码
    private String luckcode;
    //本期参与人
    private String canyuren;
    private boolean showjiexiaotime = true;
    @Override
    public void updateData(QishulistEntity qishulistEntity, ItemContext itemContext) {
            entity = qishulistEntity;
    }

    public boolean isShowjiexiaotime() {
        return showjiexiaotime;
    }

    public String getJiexiao_time() {
        String qt = entity.getQ_end_time();
        long time = qt == null ?
                System.currentTimeMillis() / 1000
                :
                qt.indexOf(".")>0 ?
                Long.parseLong(qt.substring(0,qt.indexOf("."))) : Long.parseLong(qt);
        return entity.getQiname() + "  揭晓时间："+ DreamUtils.formatSecTime(time,"yyyy年MM月dd日  HH:mm");
    }

    public String getUrl() {
        User user =  entity.getQ_user();

        return (user == null || user.getImg() == null) ? url : user.getImg();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getWinner() {
        User user = entity.getQ_user();
        return "幸运者："+ ( user == null ? "" : user.getUsername());
    }

    public String getLuckcode() {
        return "幸运号码："+entity.getQ_user_code();
    }

    public String getCanyuren() {
        return "本期参与："+entity.getGonumber_total()+"人次";
    }

}
