package com.dream.main.goodpay;

import com.alibaba.fastjson.JSON;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * @author yangll
 */
@PresentationModel
public class GoodPayPM extends TitleBarPM {

    private final String TAG_CODE = "TAG_CODE";//生成订单号

    List<Good> data = new ArrayList<Good>();
    GoodPayView view;
    public GoodPayPM(GoodPayView view) {
        this.view = view;
    }

    @ItemPresentationModel(value = GoodPayItemPM.class)
    public List<Good> getData() {
        return data;
    }

    public void setData(List<Good> data) {
        if(data != null && data.size() > 0)
            this.data.addAll(data);
        pmRefresh("data");
    }

    //跳支付界面
    public void gopay(ClickEvent event){
        view.gopay();
        //生成订单号
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", "195");
        map.put("num", "1");
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE, ProtocolUrl.PAY_DINGDAN, map);
    }

    /**
     * 处理订单号数据
     * @param response
     */
    @Subcriber(tag = TAG_CODE, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                ToastUtil.show("订单号生成成功");
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }

    }
}
