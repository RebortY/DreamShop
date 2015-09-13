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
public class DetailItemPM implements ItemPresentationModel<AccountChongzhiInfo> {

    AccountChongzhiInfo info;

    String payDate;

    public String getPayMoney() {
        return "¥" + String.valueOf(info.getMoney());
    }

    public String getPayDate() {
        return DreamUtils.formatSecTime(Long.valueOf(info.getTime()), "yyyy-MM-dd HH:mm:ss");
    }

    String payMoney;

    @Override
    public void updateData(AccountChongzhiInfo accountChongzhiInfo, ItemContext itemContext) {
        info = accountChongzhiInfo;
    }
}
