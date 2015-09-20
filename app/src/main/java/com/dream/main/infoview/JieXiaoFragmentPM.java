package com.dream.main.infoview;

import com.dream.bean.goodinfo.GoodInfo;
import com.dream.util.DreamUtils;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * @author yangll
 */
@PresentationModel
public class JieXiaoFragmentPM {

    //总需人数
    private String needCount;
    //幸运码
    private String luckCode;

    //中奖用户头像
    private String url;
    private boolean circle = true;
    //揭晓时间
    private String showTime;
    //获奖者
    private String winner;

    //晒单内容
    private String content;

    //图片比例
    private float aspectRatio = 1.33f;

    //发布时间
    private String fbtime;

    private GoodInfo info;
    JieXiaoView view = null;
    public JieXiaoFragmentPM(GoodInfo info) {
        this.info = info;
    }

    public void setView(JieXiaoView view) {
        this.view = view;
    }

    public String getNeedCount() {
        return "总需人次：" + info.getZongrenshu() + "人次";
    }

    public String getLuckCode() {
        return "幸运号码：" + info.getQ_user_code();
    }

    public String getUrl() {

        String url = info.getQ_user() == null
                ? "file://drawable/good_default.png":
                info.getQ_user().getImg() == null ? "file://drawable/good_default.png"
                        : info.getQ_user().getImg();
        return url;
    }

    public boolean isCircle() {
        return circle;
    }

    public String getShowTime() {
        return "揭晓时间：" + DreamUtils.formatSecTime(info.getXsjx_time(), "yyyy年MM月dd日 HH:mm:ss");
    }

    public String getWinner() {

        String userName = info.getQ_user() == null ? "好幸运" : info.getQ_user().getUsername();
        return "获奖用户："+userName;
    }

    public String getContent() {
        return info.getDescription();
    }

    public String getFbtime() {
//        int index = info.getQ_end_time().indexOf(".");
//        String  time = index > 0 ? info.getQ_end_time().substring(0, index) : info.getQ_end_time();
        return "发布时间：" + DreamUtils.formatSecTime(info.getTime(), "yyyy年MM月dd日 HH:mm:ss");
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void onClick(ClickEvent event){
        if(view != null) view.gotoInfo(info);
    }
}
