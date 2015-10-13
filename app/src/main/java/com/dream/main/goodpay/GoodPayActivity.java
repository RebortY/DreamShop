package com.dream.main.goodpay;

import android.content.Intent;

import com.dream.R;
import com.dream.bean.AddressListItemInfo;
import com.dream.bean.Good;
import com.dream.main.base.BaseActivity;
import com.dream.main.tabme.address.AddressActivity;
import com.dream.util.ToastUtil;
import com.github.snowdream.android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangll
 *         <p>
 *         支付页面
 */
public class GoodPayActivity extends BaseActivity implements GoodPayView {

    public static final String GOODLIST = "GOODLISTPAY";
    List<Good> infactGoods = new ArrayList<>();
    GoodPayPM pm = null;

    @Override
    public void setAttIntent(Intent intent) {
        List<Good> readygoods = intent.getParcelableArrayListExtra(GOODLIST);
        AddressListItemInfo.DataEntity.ListEntity addressInfo = (AddressListItemInfo.DataEntity.ListEntity) intent.getSerializableExtra(AddressActivity.class.getName());


        if (readygoods == null || readygoods.size() == 0) {
            ToastUtil.show("没有商品可以支付，请购买哦");
            finish();
        } else {
            HashMap<String, Good> tempMap = new HashMap<>();
            for (Good gg : readygoods) {
                Good g = tempMap.get(gg.getId());
                if (g != null) {
                    int count = g.getAddCount();
                    g.setAddCount(count + 1);
                } else {
                    infactGoods.add(gg);
                    gg.setAddCount(1);
                    tempMap.put(gg.getId(), gg);
                }
            }
            tempMap.clear();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.goodpay;
    }

    @Override
    public Object initPM() {
        pm = new GoodPayPM(this);
        pm.setData(infactGoods);
        Log.d("init PM = " + infactGoods.size());
        return pm;
    }

    //跳支付宝页面，支付， 应该处理支付成功的回调
    public void gopay() {

    }

}
