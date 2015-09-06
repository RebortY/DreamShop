package com.dream.main.tabme;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.tabpublish.GoodItemBean;
import com.dream.main.tabpublish.GoodItemPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;

import org.json.JSONException;
import org.json.JSONObject;
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

    ArrayList<AddressListItemBean.DataEntity.ListEntity> adressList = new ArrayList<AddressListItemBean.DataEntity.ListEntity>();

    private final String CODE_LIST = "CODE_LIST";
    AddressAct.AddressAdapter mAdapter;

    public AddressPM(Context context, BaseActView baseActViews, AddressAct.AddressAdapter adapter) {

        this.mAdapter = adapter;
        this.mBaseActView = baseActViews;
        this.mContext = context;
        DreamApplication.getApp().eventBus().register(this);
        DreamApplication.getApp().getDreamNet().netJsonPost(CODE_LIST, ProtocolUrl.ADDRESS_LIST, new HashMap<String, Object>());
    }

    @ItemPresentationModel(value = AddressListItemPM.class)
    public ArrayList<AddressListItemBean.DataEntity.ListEntity> getAdressList() {
        return adressList;
    }

    public void setAdressList(ArrayList<AddressListItemBean.DataEntity.ListEntity> adressList) {
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

        JSONObject jsonObj = null;
        try {
            jsonObj = ((JSONObject) response.getResp()).getJSONObject("data");
            String jsonStr = jsonObj.getJSONArray("list").toString();
            adressList = (ArrayList<AddressListItemBean.DataEntity.ListEntity>) JSON.parseArray(jsonStr, AddressListItemBean.DataEntity.ListEntity.class);

            setAdressList(adressList);
            mBaseActView.setOnClickView(null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClicks(ClickEvent event) {

        mBaseActView.setOnClickView(event.getView());
        switch (event.getView().getId()) {

            case R.id.bt_add:
                mContext.startActivity(new Intent(mContext, AddressEditAct.class));
                break;
        }
    }

    public void adressListItemClick(ItemClickEvent event){

        ToastUtil.show(String.valueOf(event.getPosition()));
    }

    @Subcriber(tag = AddressEditAct.CODE_RESULT_EDIT, threadMode = ThreadMode.MainThread)
    public void refreshAddressList(String string) {
//        DreamApplication.getApp().getDreamNet().netJsonPost(CODE_LIST, ProtocolUrl.ADDRESS_LIST, new HashMap<String, Object>());

        ToastUtil.show(string);
    }

}
