package com.dream.main.tabme;

import android.content.Context;

import com.dream.R;
import com.dream.alipay.AilPay;
import com.dream.alipay.AilPayBean;
import com.dream.bean.UpLoadHeadBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.util.UplodUtil;
import com.dream.views.AbstractPM;
import com.github.snowdream.android.util.Log;

import org.apache.commons.lang.StringUtils;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/27 18:58
 */
@PresentationModel
public class AccountPayPM extends TitleBarPM {

    BaseActView meFragmentView;

    Context mContext;

    String balance;

    String otherMoney;//其它金额

    public AccountPayPM(Context context, BaseActView baseActViews) {


        Log.d("********************" + AccountPayPM.class.getName());

        this.meFragmentView = baseActViews;
        this.mContext = context;
        DreamApplication.getApp().eventBus().register(this);
    }

    public String getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(String otherMoney) {
        this.otherMoney = otherMoney;
    }

    public String getBalance() {
        if (DreamApplication.getApp().getUser().getMoney() == 0) {
            return mContext.getResources().getString(R.string.tv_money_now, "0");
        }
        return mContext.getResources().getString(R.string.tv_money_now, String.valueOf(DreamApplication.getApp().getUser().getMoney()));
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void onClicks(ClickEvent event) {

        switch (event.getView().getId()) {
            case R.id.bt_pay:
                AilPayBean bean = new AilPayBean();
                if (AccountPayAct.isOther) {
                    if (!StringUtils.isEmpty(getOtherMoney())) {
                        AccountPayAct.allMoney = getOtherMoney();
                    }
                }
                bean.setOrderNum(DreamApplication.ailPay().getOutTradeNo());
                bean.setPrice(AccountPayAct.allMoney);
                bean.setSubject("元梦购充值");
                bean.setBody("元梦购充值");
                DreamApplication.getApp().eventBus().post(bean, AilPay.TAG_ALIPAY_CREAT_CHONGZHI);
                break;
        }
    }

    @Override
    public String getTitleBar() {
        return mContext.getResources().getString(R.string.tv_pay);
    }

    @Subcriber(tag = UplodUtil.COD_UPLOD, threadMode = ThreadMode.MainThread)
    public void postRespHandler(UpLoadHeadBean headBean) {
    }
}
