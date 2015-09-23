package com.dream.main.tabme.account;

import com.dream.bean.AccountChongzhiInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/13 17:25
 */
public class ChongzhiDetailItemPM implements ItemPresentationModel<AccountChongzhiInfo> {

    AccountChongzhiInfo info;

    String payDate;

    String payMoney;

    public String getPayMoney() {
        return  "充值金额：" + "¥" + String.valueOf(info.getMoney());
    }

    public String getPayDate() {
        return "充值时间：" + DreamUtils.formatSecTime(Long.valueOf(info.getTime()), "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void updateData(AccountChongzhiInfo accountChongzhiInfo, ItemContext itemContext) {
        info = accountChongzhiInfo;
    }
}
