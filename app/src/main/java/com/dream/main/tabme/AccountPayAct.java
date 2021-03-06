package com.dream.main.tabme;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Switch;

import com.dream.R;
import com.dream.alipay.AilPay;
import com.dream.alipay.AilPayBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.main.goodpay.GoodPayPM;
import com.dream.net.NetResponse;
import com.dream.util.ToastUtil;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/27 16:50
 * 充值
 */
public class AccountPayAct extends BaseActivity implements BaseActView {

    private final String TAG_NUM = "TAG_NUM";//获取订单号

    @Bind(R.id.gridView)
    GridView gridView;

    @Bind(R.id.arrow_aili)
    ImageView checkBox;//阿里支付

    @Bind(R.id.editText2)
    EditText etMoney;//其它金额

    List<Map<String, Object>> itemsList;
    AccountPayPM accountPayPM;
    SimpleAdapter adapter;

    private int typeLastItem = 0;
    public static String allMoney = "10";//支付总额
    public static boolean isOther;// 是否为其它金额  true是   false否

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

        initGridView();
    }

    @Override
    public void setOnClickView(View view) {

        switch(view.getId()){
            case R.id.layout_check_aili:
                if(accountPayPM.isAli){
                    checkBox.setImageDrawable(getResources().getDrawable(R.drawable.check_def));
                    accountPayPM.isAli = false;
                }else{
                    checkBox.setImageDrawable(getResources().getDrawable(R.drawable.check_un));
                    accountPayPM.isAli = true;
                }
                break;
        }

    }

    private void initGridView() {

        gridView = (GridView) findViewById(R.id.gridView);
        itemsList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < getResources().getStringArray(R.array.pay_array).length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (i == 0) {
                map.put("radioIcon", R.drawable.border_box_red_select);//radiobutton_off
            } else {
                map.put("radioIcon", R.drawable.border_box_gray_normal);//radiobutton_off
            }

            if (i == getResources().getStringArray(R.array.pay_array).length - 1) {
                map.put("text", getResources().getStringArray(R.array.pay_array)[i]);
            } else {
                map.put("text", getResources().getStringArray(R.array.pay_array)[i] + "元");
            }

            itemsList.add(map);
        }

        adapter = new SimpleAdapter(this, itemsList, R.layout.gridview_item_moeny,
                new String[]{"radioIcon", "text"}, new int[]{R.id.imageView4, R.id.checkbox_money});

        gridView.setAdapter(adapter);
        gridView.requestFocus();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {

                if (typeLastItem != position) {
                    if (typeLastItem >= 0) {
                        changeItemImg(adapter, typeLastItem, false);
                    }
                }

                if (position == getResources().getStringArray(R.array.pay_array).length - 1) {
                    etMoney.setVisibility(View.VISIBLE);
                    isOther = true;
                } else {
                    etMoney.setVisibility(View.GONE);
                    allMoney = getResources().getStringArray(R.array.pay_array)[position];
                    isOther = false;
                }

                changeItemImg(adapter, position, true);
                typeLastItem = position;
            }
        });
    }

    private void changeItemImg(SimpleAdapter sa, int selectedItem, boolean isOn) {
        HashMap<String, Object> map = (HashMap<String, Object>) sa.getItem(selectedItem);
        if (isOn) {
            map.put("radioIcon", R.drawable.border_box_red_select);
        } else {
            map.put("radioIcon", R.drawable.border_box_gray_normal);
        }
        sa.notifyDataSetChanged();
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
    @Subcriber(tag = AilPay.TAG_ALIPAY_OK_CHONGZHI, threadMode = ThreadMode.MainThread)
    public void respHandlerPay(String msg) {

        int tempMoney = DreamApplication.getApp().getUser().getMoney() + Integer.valueOf(allMoney);
        DreamApplication.getApp().getUser().setMoney(tempMoney);
        DreamApplication.getApp().eventBus().post(GoodPayPM.TAG_SHOP_ALIPAY_OK);
        finish();
    }

}
