package com.dream.main.tabme.address;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.dream.R;
import com.dream.bean.AddressListItemInfo;
import com.dream.main.DreamApplication;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
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
 * 15/9/14 23:27
 */
@PresentationModel
public class AddressActivityPM extends TitleBarPM {

    private final String TAG_GET_ADDRESS = "TAG_GET_ADDRESS";
    public static final String INTENT_AddressActivityPM = "INTENT_AddressActivityPM";

    Context mContext;

    private boolean loadEnable = false;

    private List<AddressListItemInfo.DataEntity.ListEntity> data = new ArrayList<>();

    private MaterialPullRefreshEvent tempPullEvent;

    AddressView view;

    AddressActivityPM(Context context, AddressView views) {

        this.view = views;
        this.mContext = context;
        DreamApplication.getApp().eventBus().register(this);
    }

    public void getDatas() {

        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_GET_ADDRESS, ProtocolUrl.ADDRESS_LIST, new HashMap<String, Object>());
    }

    public void refresh(MaterialPullRefreshEvent event) {
        tempPullEvent = event;
        getDatas();
    }


    @Subcriber(tag = TAG_GET_ADDRESS, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {


        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                List<AddressListItemInfo.DataEntity.ListEntity> commentInfos = JSON.parseArray(array.toString(), AddressListItemInfo.DataEntity.ListEntity.class);
                data.clear();
                data.addAll(commentInfos);
                pmRefresh("data");
            } catch (JSONException e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
        if (tempPullEvent != null)
            view.stopRefresh(tempPullEvent.getView());


    }

    public void onClicks(ClickEvent event) {

        switch (event.getView().getId()) {
            case R.id.bt_reg:
                Intent intent = new Intent(mContext, AddressEditAct.class);
                mContext.startActivity(intent);
                break;
        }
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(boolean loadEnable) {
        this.loadEnable = loadEnable;
    }

    @ItemPresentationModel(value = AddressListItemPM.class, factoryMethod = "addressListItemPM")
    public List<AddressListItemInfo.DataEntity.ListEntity> getData() {
        return data;
    }

    public AddressListItemPM addressListItemPM() {
        return new AddressListItemPM(view);
    }

    public void onItemClick(ItemClickEvent event) {
        AddressListItemInfo.DataEntity.ListEntity bean = (AddressListItemInfo.DataEntity.ListEntity) event.getParent().getAdapter().getItem(event.getPosition());
        view.intentInfoView(bean);
    }

    @Subcriber(tag = AddressActivity.CODE_RESULT_EDIT, threadMode = ThreadMode.Async)
    public void onEvents(String str ) {
        getDatas();
    }

}
