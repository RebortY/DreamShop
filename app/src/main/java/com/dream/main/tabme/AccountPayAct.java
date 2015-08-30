package com.dream.main.tabme;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/27 16:50
 * 充值
 */
public class AccountPayAct extends BaseActivity implements BaseActView {

    @Bind(R.id.gridView)
    GridView gridView;

    List<Map<String, Object>> itemsList;
    AccountPayPM accountPayPM;
    SimpleAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_pay;
    }

    @Override
    public Object initPM() {
        accountPayPM = new AccountPayPM(this);
        DreamApplication.getApp().eventBus().register(this);
        return accountPayPM;
    }

    @Override
    public void initView() {
        initGridView();
    }

    @Override
    public void setOnClickView(View view) {

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


}
