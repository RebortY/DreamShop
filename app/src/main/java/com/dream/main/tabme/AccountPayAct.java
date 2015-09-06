package com.dream.main.tabme;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dream.R;
import com.dream.alipay.AilPay;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.NetResponse;
import com.dream.util.ToastUtil;

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
    AilPay ailPay;

    Handler handler;

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
        payHander();
    }

    @Override
    public void setOnClickView(View view) {

        switch (view.getId()) {
            case R.id.bt_pay:
                ailPay.pay();
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

    /**
     * 处理支付返回结果
     *
     * @param
     */
    private void payHander(){
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                ailPay.isPayResult((String) msg.obj);
            }
        };

        ailPay = new AilPay(this, handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }

}
