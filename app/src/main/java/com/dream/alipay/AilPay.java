package com.dream.alipay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dream.main.DreamApplication;
import com.dream.main.MainActivity;
import com.dream.main.tabme.AccountPayPM;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

public class AilPay {

    public static final String TAG_ALIPAY_CHONGZHI = "TAG_ALIPAY_CHONGZHI";//调充值支付接口
    public static final String TAG_ALIPAY_ZHIFU = "TAG_ALIPAY_ZHIFU";//调商品支付接口
    public static final String TAG_CHECK_PAY = "TAG_CHECK_PAY";//检查支付
    public static final String TAG_ALIPAY_OK_CHONGZHI = "TAG_ALIPAY_OK_CHONGZHI";//充值支付成功完成
    public static final String TAG_ALIPAY_OK_ZHIFU = "TAG_ALIPAY_OK_ZHIFU";//商品支付成功完成
    public static final String TAG_ALIPAY_ERROR_ZHIFU = "TAG_ALIPAY_ERROR_ZHIFU";//商品支付失败
    public static final String TAG_ALIPAY_CREAT_CHONGZHI = "TAG_ALIPAY_CREAT_CHONGZHI";//创建充值支付信息
    public static final String TAG_ALIPAY_CREAT_ZHIFU = "TAG_ALIPAY_CREAT_ZHIFU";//创建商品支付信息

    // 商户PID
    private static final String PARTNER = "2088911900880534";
    // 商户收款账号
    private static final String SELLER = "goxmytec@163.com";
    // 商户私钥，pkcs8格式
    private static final String RSA_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANmaQMEmIrj1meiNhWtCPESK635vw0uxtuqGDyPNAUOZToI9FsIo5JAZh/DIUUJi8DAbR1r/38EbkwXacDizzzuhUmXmSBpV8DtfMrwwqwT9YAytYBXyBhrf/IJYqwHj2hbKG39cTG2AWocZZ3lD09ukhplNMNN5psMylr44BdxzAgMBAAECgYA3z5l2MLrNJ6kO/WVP6lAc4GigKDg17OHWawXtD8KYPPTm8JYqvGRLFVFJj4dP+KJUJFr72w7fr5vY73rDAFNRJdckT2sdjLf/LND072Rgo0BvtmVRv9ZqQShw+qqOBm1q0iGFolz8pKQDBH6FXPnL9IYtf9ZPbKwAHLoTTAXb+QJBAPWhiZVdhASQ81wzTT87H9Bkb+qEd1hBQ+uj/B6lpx4ZQWcHDhPYGjO6AYrkpu6nPEgiFRZvld0NPoFMgazl6TUCQQDiydH3Z048smSGZlC8oVDfSzhC6SQ5yY8+QuVONSqCUtbTVaFV0Rsm9/oqIzXSg6W8xAH/aYMi+29UP1jDVgwHAkBFAfkRSZCJZ9+3C4KsPQQXG+CFVmnhFZTrYsq2IXvt7NJ/C3BdCt1xotREHTD442sAKPMbtoW38Lyavs+/r2fxAkAR5OC3R6Rpr5206Q/HwmayJ6o5xVGBacDupbKYGCPRgp3GsQP41HXy/i/z8TFG+fIy0bhiob2iHfqryA/F4d0zAkEAirYeU/LsN0AyDdt7HA6DggCCYkKZvfEUfXKZCD4hEhDBhxSacVruXhBvzeZtq5yWRG9YD+fbWqsNB93fXjqefA==";
    // 支付宝公钥
    private static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";


    Context mContext;

    public AilPay(Context context) {

        Log.d("********************" + AilPay.class.getName());

        this.mContext = context;
        try{
            DreamApplication.getApp().eventBus().register(this);
        }catch (Exception e){

        }
    }

    public void colsEvenBus(){
        DreamApplication.getApp().eventBus().unregister(this);
    }

    /**
     * check whether the device has authentication alipay account.
     * 查询终端设备是否存在支付宝认证账户
     */
    public void check() {
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask payTask = new PayTask((Activity) mContext);
                // 调用查询接口，获取查询结果
                boolean isExist = payTask.checkAccountIfExist();

                DreamApplication.getApp().eventBus().post(isExist, TAG_CHECK_PAY);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    /**
     * call alipay sdk pay. 调用SDK支付 for 充值
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_CREAT_CHONGZHI, threadMode = ThreadMode.MainThread)
    public void pay(AilPayBean bean) {

        createPay(bean, 0);
    }

    /**
     * call alipay sdk pay. 调用SDK支付 for 商品支付
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_CREAT_ZHIFU, threadMode = ThreadMode.MainThread)
    public void pay_shop(AilPayBean bean) {

        createPay(bean, 1);
    }

    /**
     * 构建支付数据
     * type 0=充值
     * type 1=商品支付
     */
    public void createPay(AilPayBean bean, int type){
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            Toast.makeText(mContext, "验证失败", Toast.LENGTH_LONG).show();
            return;
        }
        String orderInfo = getOrderInfo(bean);

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                // 调用支付接口，获取支付结果
                if(!DreamUtils.isEmpty(payInfo)){
                    String result = MainActivity.alipay.pay(payInfo);
                    if(type == 0){
                        DreamApplication.getApp().eventBus().post(result, TAG_ALIPAY_CREAT_CHONGZHI);
                    }
                    if(type == 1){
                        DreamApplication.getApp().eventBus().post(result, TAG_ALIPAY_CREAT_ZHIFU);
                    }
                }else{
                    ToastUtil.show("支付错误");
                    return;
                }
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(AilPayBean ailPayBean) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + ailPayBean.getOrderNum() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + ailPayBean.getSubject() + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + ailPayBean.getBody() + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + ailPayBean.getPrice() + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    public boolean isPayResult(String result) {

        PayResult payResult = new PayResult(result);

        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        String resultInfo = payResult.getResult();

        String resultStatus = payResult.getResultStatus();

        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        if (TextUtils.equals(resultStatus, "9000")) {
            ToastUtil.show("支付成功");
            return true;
        } else {
            // 判断resultStatus 为非“9000”则代表可能支付失败
            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                ToastUtil.show("支付结果确认中");
            } else {
                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                ToastUtil.show("支付失败");
            }
            return false;
        }
    }

    /**
     * 充值
     * @param msg
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_CREAT_CHONGZHI, threadMode = ThreadMode.MainThread)
    public void onEvent(String msg) {
        if (isPayResult(msg)) {
            DreamApplication.getApp().eventBus().post("TAG_ALIPAY_OK", TAG_ALIPAY_OK_CHONGZHI);
//            colsEvenBus();
        }
    }

    /**
     * 商品支付
     * @param msg
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_CREAT_ZHIFU, threadMode = ThreadMode.MainThread)
    public void onEventPay(String msg) {
        if (isPayResult(msg)) {
            DreamApplication.getApp().eventBus().post("TAG_ALIPAY_OK", TAG_ALIPAY_OK_ZHIFU);
//            colsEvenBus();
        }else{
            DreamApplication.getApp().eventBus().post("TAG_ALIPAY_ERROR_ZHIFU", TAG_ALIPAY_ERROR_ZHIFU);
        }
    }

}
