package com.dream.main.tabme;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dream.R;
import com.dream.alipay.AilPay;
import com.dream.alipay.AilPayBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.NetResponse;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/27 16:50
 * 充值
 */
public class AccountPayAct extends BaseActivity implements BaseActView{

    private final String TAG_NUM = "TAG_NUM";//获取订单号

    @Bind(R.id.gridView)
    GridView gridView;

    List<Map<String, Object>> itemsList;
    AccountPayPM accountPayPM;
    SimpleAdapter adapter;
    AilPay ailPay = new AilPay(this);


    @Override
    public int getLayoutId() {
        return R.layout.activity_account_pay;
    }

    @Override
    public Object initPM() {
        accountPayPM = new AccountPayPM(this, this);
        return accountPayPM;
    }

    @Override
    public void initView() {
        DreamApplication.getApp().eventBus().register(this);
        initGridView();
    }

    @Override
    public void setOnClickView(View view) {

        switch (view.getId()) {
            case R.id.bt_pay:
                AilPayBean bean = new AilPayBean();
                bean.setOrderNum(ailPay.getOutTradeNo());
                bean.setPrice("0.02");
                bean.setSubject("测试商品名称");
                bean.setBody("测试商品描述");
                DreamApplication.getApp().eventBus().post(bean,AilPay.TAG_ALIPAY_CREAT);

                break;
        }

    }

    private void initGridView() {

        gridView = (GridView) findViewById(R.id.gridView);
        itemsList = new ArrayList<Map<String, Object>>();
        for (String str : getResources().getStringArray(R.array.pay_array)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", str);
            itemsList.add(map);
        }

        adapter = new SimpleAdapter(this, itemsList, R.layout.gridview_item_moeny,
                new String[]{"text"}, new int[]{R.id.checkbox_money});

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {

                ToastUtil.show(getResources().getStringArray(R.array.pay_array)[position]);
            }
        });
    }

    @Subcriber(tag = TAG_NUM, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
        } else {
            ToastUtil.show(R.string.net_error);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

    /**
     * 处理支付成功返回结果
     *
     * @param
     */
    @Subcriber(tag = AilPay.TAG_ALIPAY_OK, threadMode = ThreadMode.MainThread)
    public void respHandlerPay(String msg) {
            finish();
    }

}
