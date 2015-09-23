package com.dream.main.tabme.address;

import android.view.View;

import com.dream.bean.AddressListItemInfo;
import com.dream.bean.Good;
import com.dream.bean.GoodForm;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/16 21:24
 */
public interface AddressView {

    void onClick(View view , AddressListItemInfo.DataEntity.ListEntity form);
    void stopRefresh(View view);
    void intentInfoView(AddressListItemInfo.DataEntity.ListEntity addressInfo);
}
