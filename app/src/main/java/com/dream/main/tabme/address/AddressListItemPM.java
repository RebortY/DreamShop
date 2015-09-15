package com.dream.main.tabme.address;

import com.dream.bean.AddressListItemInfo;
import com.dream.bean.MyDreamRecordingInfo;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/14 23:33
 */
public class AddressListItemPM implements ItemPresentationModel<AddressListItemInfo.DataEntity.ListEntity> {

    AddressListItemInfo.DataEntity.ListEntity info;

    String addressName;

    String addressMobile;

    String addressSheng;

    String addressShi;

    String addressXian;

    String addressDetail;

    @Override
    public void updateData(AddressListItemInfo.DataEntity.ListEntity addressListItemInfo, ItemContext itemContext) {
        info = addressListItemInfo;
    }

    public String getAddressName() {
        return info.getShouhuoren();
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public String getAddressSheng() {
        return addressSheng;
    }

    public String getAddressShi() {
        return addressShi;
    }

    public String getAddressXian() {
        return addressXian;
    }

    public String getAddressDetail() {
        return addressDetail;
    }
}
