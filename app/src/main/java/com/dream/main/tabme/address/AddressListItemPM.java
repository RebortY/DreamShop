package com.dream.main.tabme.address;

import com.dream.bean.AddressListItemInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.github.snowdream.android.util.Log;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:33
 */
public class AddressListItemPM implements ItemPresentationModel<AddressListItemInfo.DataEntity.ListEntity> {


    AddressListItemInfo.DataEntity.ListEntity info = new AddressListItemInfo.DataEntity.ListEntity();

    AddressView addressView;

    AddressListItemPM(AddressView addressViews){
        this.addressView = addressViews;
    }


    @Override
    public void updateData(AddressListItemInfo.DataEntity.ListEntity addressListItemInfo, ItemContext itemContext) {
        info = addressListItemInfo;
    }

    public String getAddressCount() {
        return "收货地址";
    }

    public String getAddressName() {
        return info.getShouhuoren();
    }

    public String getAddressMobile() {
        return info.getMobile();
    }

    public String getAddressSheng() {
        return info.getSheng() + "-" + info.getShi() + "-" + info.getXian();
    }

    public String getAddressDetail() {
        return info.getJiedao();
    }

    public void onclick(ClickEvent event) {
        addressView.onClick(event.getView(), info);
    }

}
