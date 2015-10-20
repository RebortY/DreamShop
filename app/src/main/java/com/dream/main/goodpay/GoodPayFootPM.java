package com.dream.main.goodpay;

import com.dream.bean.AddressListItemInfo;

import org.robobinding.annotation.PresentationModel;

/**
 * @author yangll
 */
@PresentationModel
public class GoodPayFootPM {

    private String address = null;

    public GoodPayFootPM(AddressListItemInfo.DataEntity.ListEntity addressInfo) {
        address ="收货地址："+ addressInfo.getSheng() + " "+ addressInfo.getShi() + " "+addressInfo.getXian()
                + " "+addressInfo.getJiedao() + "\n"+ "邮编："+addressInfo.getYoubian()+ "\n"
                +"收货人："+addressInfo.getShouhuoren();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
