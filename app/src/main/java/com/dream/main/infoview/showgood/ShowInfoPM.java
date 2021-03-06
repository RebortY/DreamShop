package com.dream.main.infoview.showgood;

import com.dream.bean.GoodForm;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.util.DreamUtils;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/8.
 */
@PresentationModel
public class ShowInfoPM extends TitleBarPM{

    //商品图片
    private String goodUrl;
    //商品标题
    private String goodTitle;
    //参与人数
    private String needCount;

    //总人数
    private String zongCount;

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

    private GoodForm good = null;
    private ShowInfoView view;

    //图片数据
    private List<String> data = new ArrayList<>();

    public ShowInfoPM(ShowInfoView view) {
        this.view = view;
        setTitleBar("晒单");
    }

    public void setGood(GoodForm good) {
        this.good = good;
        if(good.getSd_photolist() != null && good.getSd_photolist().size() > 0){
            data.addAll(good.getSd_photolist());
            pmRefresh("data");
        }
    }

    //点击计算详情按钮
    public void onClick(ClickEvent event){
        view.goCountResult();
    }

    public String getGoodUrl() {
        return good.getThumb();
    }

    public String getGoodTitle() {
        return good.getTitle();
    }

    public String getNeedCount() {
        return "参与人次："+good.getCanyurenshu()+"人次";
    }

    public String getZongCount() {
        return "总需人次："+ good.getZongrenshu()+"人次";
    }

    public String getLuckCode() {
        return "幸运号码："+good.getQ_user_code();
    }

    public String getUrl() {
        return good.getQ_user().getImg();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getShowTime() {
        return "揭晓时间："+DreamUtils.formatSecTime(good.getSd_time() , "yyyy年MM月dd日 HH:mm:ss");
    }

    public String getWinner() {
        return good.getUsername();
    }

    public String getContent() {
        return good.getSd_content();
    }

    public String getFbtime() {
        return "发布时间："+DreamUtils.formatSecTime(good.getTime(),"yyyy年MM月dd日 HH:mm:ss");
    }

    public GoodForm getGood() {
        return good;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    @ItemPresentationModel(value = ShowItemPM.class)
    public List<String> getData() {
        return data;
    }
}
