package com.dream.main.goodpay;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dream.alipay.AilPay;
import com.dream.alipay.AilPayBean;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.DingdanBean;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.dream.main.tabme.AccountPayAct;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.shopcart.ShopCart;
import com.dream.util.ToastUtil;

import org.apache.commons.lang.StringUtils;
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

    Context mContext;

    List<Good> data = new ArrayList<Good>();
    GoodPayView view;

    StringBuffer shopDetail = new StringBuffer();//商品描述
    double shopMoney;//商品总价

    public GoodPayPM(Context context, GoodPayView view) {
        this.mContext = context;
        this.view = view;
        DreamApplication.getApp().eventBus().register(this);
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

        //生成订单号
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (Good good : data){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", Integer.valueOf(good.getId()));
            map.put("num", good.getAddCount());
            list.add(map);

            shopDetail.append(good.getTitle() + "  *" + String.valueOf(good.getAddCount()) + "\n");
            shopMoney += Double.valueOf(good.getMoney());
        }

        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE, ProtocolUrl.PAY_DINGDAN, list);
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
                DingdanBean dingdanBean = JSON.parseObject(obj.getJSONObject("data").toString(), DingdanBean.class);

                AilPayBean bean = new AilPayBean();
                bean.setOrderNum(dingdanBean.getDingdancode());
                bean.setPrice(String.valueOf("0.1"));
                bean.setSubject(shopDetail.toString());
                bean.setBody(shopDetail.toString());
//                DreamApplication.getApp().eventBus().post(bean, AilPay.TAG_ALIPAY_CREAT_ZHIFU);
                DreamApplication.ailPay().createPay(bean, 1);
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
    }

    /**
     * 处理支付成功返回结果
     *
     * @param
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_OK_ZHIFU, threadMode = ThreadMode.MainThread)
    public void respHandlerPay(String msg) {
        for(Good dood : data){
            ShopCart.getShopCart().removeShop(dood);
        }
        view.gopay();
    }
}
