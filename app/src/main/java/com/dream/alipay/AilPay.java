package com.dream.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dream.main.tabme.AccountPayAct;
import com.dream.util.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AilPay {

    // 商户PID
    private static final String PARTNER = "2088911900880534";
    // 商户收款账号
    private static final String SELLER = "goxmytec@163.com";
    // 商户私钥，pkcs8格式
    private static final String RSA_PRIVATE =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANmaQMEmIrj1meiNhWtCPESK635vw0uxtuqGDyPNAUOZToI9FsIo5JAZh/DIUUJi8DAbR1r/38EbkwXacDizzzuhUmXmSBpV8DtfMrwwqwT9YAytYBXyBhrf/IJYqwHj2hbKG39cTG2AWocZZ3lD09ukhplNMNN5psMylr44BdxzAgMBAAECgYA3z5l2MLrNJ6kO/WVP6lAc4GigKDg17OHWawXtD8KYPPTm8JYqvGRLFVFJj4dP+KJUJFr72w7fr5vY73rDAFNRJdckT2sdjLf/LND072Rgo0BvtmVRv9ZqQShw+qqOBm1q0iGFolz8pKQDBH6FXPnL9IYtf9ZPbKwAHLoTTAXb+QJBAPWhiZVdhASQ81wzTT87H9Bkb+qEd1hBQ+uj/B6lpx4ZQWcHDhPYGjO6AYrkpu6nPEgiFRZvld0NPoFMgazl6TUCQQDiydH3Z048smSGZlC8oVDfSzhC6SQ5yY8+QuVONSqCUtbTVaFV0Rsm9/oqIzXSg6W8xAH/aYMi+29UP1jDVgwHAkBFAfkRSZCJZ9+3C4KsPQQXG+CFVmnhFZTrYsq2IXvt7NJ/C3BdCt1xotREHTD442sAKPMbtoW38Lyavs+/r2fxAkAR5OC3R6Rpr5206Q/HwmayJ6o5xVGBacDupbKYGCPRgp3GsQP41HXy/i/z8TFG+fIy0bhiob2iHfqryA/F4d0zAkEAirYeU/LsN0AyDdt7HA6DggCCYkKZvfEUfXKZCD4hEhDBhxSacVruXhBvzeZtq5yWRG9YD+fbWqsNB93fXjqefA==";
    // 支付宝公钥
    private static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    public static final int SDK_PAY_FLAG = 1;
    public static final int SDK_CHECK_FLAG = 2;

    Context mContext;
    Handler mHandler;

    public AilPay(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
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

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = isExist;
                mHandler.sendMessage(msg);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            Toast.makeText(mContext, "验证失败", Toast.LENGTH_LONG).show();
            return;
        }
        // 订单
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

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
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

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
        }

        return false;
    }
}
