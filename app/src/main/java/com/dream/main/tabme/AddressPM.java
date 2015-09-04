package com.dream.main.tabme;

import android.content.Context;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/4 20:13
 */
@PresentationModel
public class AddressPM extends AbstractPM {

    BaseActView mBaseActView;
    Context mContext;

    ArrayList<AddressListItemPM> adressList = new ArrayList<AddressListItemPM>();

    private final String CODE_LIST = "CODE_LIST";


    public AddressPM(Context context, BaseActView baseActViews) {

        this.mBaseActView = baseActViews;
        this.mContext = context;
        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonPost(CODE_LIST, ProtocolUrl.ADDRESS_LIST, new HashMap<String, Object>());
    }

    @ItemPresentationModel(value = AddressListItemPM.class)
    public ArrayList<AddressListItemPM> getAdressList() {
        return adressList;
    }

    public void setAdressList(ArrayList<AddressListItemPM> adressList) {
        this.adressList = adressList;
    }

    @Subcriber(tag = CODE_LIST, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            respAddressList(response, CODE_LIST);
        } else {
            ToastUtil.show(R.string.net_error);
        }
    }

    private void respAddressList(NetResponse response, String tag){


    }

    public void onClicks(ClickEvent event) {

        mBaseActView.setOnClickView(event.getView());
        switch (event.getView().getId()) {

            case R.id.bt_add:
                break;
        }
    }

    public void adressListItemClick(ItemClickEvent event){

    }


}
