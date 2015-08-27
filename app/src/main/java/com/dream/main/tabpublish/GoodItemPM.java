package com.dream.main.tabpublish;

import com.dream.bean.Good;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * Created by yangll on 15/8/27.
 */

public class GoodItemPM implements ItemPresentationModel<GoodItemBean> {

    //图片的url
    private String url;
    //商品标题
    private String title;
    //获奖者
    private String user;
    //参与人数
    private String joinCount;
    //幸运号码
    private String code;
    //揭晓时间
    private String showTime;

    private Good good = null;
    @Override
    public void updateData(GoodItemBean goodItemBean, ItemContext itemContext) {
        good = goodItemBean.getGood();
    }

    public String getUrl() {
        return good.getThumb();
    }

    public String getTitle() {
        return good.getTitle();
    }

    public String getUser() {
        return good.getQ_user();
    }

    public String getJoinCount() {
        return good.getCanyurenshu();
    }

    public String getCode() {
        return good.getQ_user_code();
    }

    public String getShowTime() {
        return good.getQ_end_time();
    }
}
