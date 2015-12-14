package com.dream.main.goodpay;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dream.alipay.AilPay;
import com.dream.alipay.AilPayBean;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.DingdanBean;
import com.dream.bean.Good;
import com.dream.bean.PayMoney;
import com.dream.main.DreamApplication;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.shopcart.ShopCart;
import com.dream.util.ToastUtil;

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
    private final String TAG_CODE_MONEY = "TAG_CODE_MONEY";//支付前获得商品余额
    private final String TAG_CODE_MONEY_PAY = "TAG_CODE_MONEY_PAY";//账户余额支付
    private final String TAG_PAY_END = "TAG_PAY_END";//支付成功

    public static final String TAG_SHOP_ALIPAY_OK = "TAG_SHOP_ALIPAY_OK";

    Context mContext;

    List<Good> data = new ArrayList<Good>();
    GoodPayView view;
    DingdanBean dingdanBean;

    StringBuffer shopDetail = new StringBuffer();//商品描述
    double shopMoney;//商品总价

    GoodPayFootPM footpm = null;
    boolean pay_checkbox;

    ArrayList<HashMap<String, Object>> list;//购物车商品list

    public GoodPayPM(Context context, GoodPayView view,AddressListItemInfo.DataEntity.ListEntity address) {
        this.mContext = context;
        this.view = view;
        this.footpm = new GoodPayFootPM(address);
        DreamApplication.getApp().eventBus().register(this);
    }

    @ItemPresentationModel(value = GoodPayItemPM.class)
    public List<Good> getData() {
        return data;
    }

    public GoodPayFootPM getFootpm() {
        return footpm;
    }

    public void setData(List<Good> data) {
        if(data != null && data.size() > 0)
            this.data.addAll(data);
        pmRefresh("data");
    }


    //跳支付界面
    public void gopay(ClickEvent event){

        //生成订单号
         list = new ArrayList<>();
        shopMoney = 0;
        for (Good good : data){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", Integer.valueOf(good.getId()));
            map.put("num", good.getAddCount());
            list.add(map);

            shopDetail.append(good.getTitle() + "  *" + String.valueOf(good.getAddCount()) + "\n");
//            shopMoney += Double.valueOf(good.getMoney());

            shopMoney += Double.valueOf(good.getAddCount());//每个商品1元,总价相当于商品个数
        }


//        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE_MONEY, ProtocolUrl.PAY_MONEY, list);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE, ProtocolUrl.PAY_DINGDAN, list);
    }

    /**
     * 支付前调用获取余额接口
     * @param response
     */
    @Subcriber(tag = TAG_CODE_MONEY, threadMode = ThreadMode.MainThread)
    public void getMoneyRespHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                PayMoney payMoney = JSON.parseObject(obj.getJSONObject("data").toString(), PayMoney.class);

                if(payMoney.getMoney() >= shopMoney){
                    ToastUtil.show("账户余额支付");
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    pay_checkbox = true;
                    map.put("pay_checkbox", pay_checkbox);
                    map.put("list", list);
                    DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE_MONEY_PAY, ProtocolUrl.PAY_DINGDAN_2, map);
                }else{
                    pay_checkbox = false;
                    DreamApplication.getApp().getDreamNet().netJsonPost(TAG_CODE, ProtocolUrl.PAY_DINGDAN, list);
                }
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
    }

    /**
     * 支付前调用获取余额接口返回数据处理
     * @param response
     */
    @Subcriber(tag = TAG_CODE_MONEY_PAY, threadMode = ThreadMode.MainThread)
    public void moneyRespHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject obj = (JSONObject) response.getResp();
                dingdanBean = JSON.parseObject(obj.getJSONObject("data").toString(), DingdanBean.class);

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("dingdancode", dingdanBean.getDingdancode());
                map.put("trade_no", dingdanBean.getDingdancode());
                map.put("pay_checkbox", pay_checkbox);
                DreamApplication.getApp().getDreamNet().netJsonPost(TAG_PAY_END, ProtocolUrl.PAY_END, map);
            } catch (Exception e) {
                ToastUtil.show("数据异常");
            }
        } else {
            ToastUtil.show("获取数据失败");
        }
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
                dingdanBean = JSON.parseObject(obj.getJSONObject("data").toString(), DingdanBean.class);

                AilPayBean bean = new AilPayBean();
                bean.setOrderNum(dingdanBean.getDingdancode());
                bean.setPrice(String.valueOf(shopMoney));
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

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("dingdancode", dingdanBean.getDingdancode());
        map.put("trade_no", dingdanBean.getDingdancode());
        map.put("pay_checkbox", pay_checkbox);
        DreamApplication.getApp().getDreamNet().netJsonPost(TAG_PAY_END, ProtocolUrl.PAY_END, map);

        for(Good dood : data){
            ShopCart.getShopCart().removeShop(dood);
        }

        if(pay_checkbox){
            //支付成功通知UI更新
            double tempMoney = DreamApplication.getApp().getUser().getMoney() - Double.valueOf(shopMoney);
            DreamApplication.getApp().getUser().setMoney((int)tempMoney);
            DreamApplication.getApp().eventBus().post(TAG_SHOP_ALIPAY_OK);
        }
        view.gopay();
    }

    /**
     * 支付成功通知服务器
     *
     * @param
     */
    @Subcriber(tag = TAG_PAY_END, threadMode = ThreadMode.MainThread)
    public void respHandlerPayEnd(NetResponse response) {

        if (response.getRespType() == NetResponse.SUCCESS) {

        } else {
            ToastUtil.show("获取数据失败");
        }
    }

    /**
     * 处理支付失败
     *
     * @param
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_ERROR_ZHIFU, threadMode = ThreadMode.MainThread)
    public void respHandlerPayErrar(String msg) {
        pmRefresh("data");
    }
}
