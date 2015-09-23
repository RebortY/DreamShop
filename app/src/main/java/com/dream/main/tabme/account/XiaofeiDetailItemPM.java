package com.dream.main.tabme.account;

import com.dream.bean.AccountChongzhiInfo;
import com.dream.bean.AccountXiaofeiInfo;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/23 00:11
 */
public class XiaofeiDetailItemPM implements ItemPresentationModel<AccountXiaofeiInfo> {

    AccountXiaofeiInfo info;

    String payDate;

    String payMoney;

    public String getPayMoney() {
        return "消费金额：" + "¥" + String.valueOf(info.getMoney());
    }

    public String getPayDate() {
        return "消费时间：" + DreamUtils.formatSecTime(Long.valueOf(info.getTime()), "yyyy-MM-dd HH:mm:ss");
    }


    @Override
    public void updateData(AccountXiaofeiInfo accountChongzhiInfo, ItemContext itemContext) {
        info = accountChongzhiInfo;
    }
}
