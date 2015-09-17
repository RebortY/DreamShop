package com.dream.main.shopcart;

import android.view.View;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.base.BaseActivity;
import com.dream.util.ToastUtil;

import java.util.List;

/**
 * @author yangll
 */
public class ShopCartActivity extends BaseActivity implements ShopCartView{

    @Override
    public int getLayoutId() {
        return R.layout.shopcart;
    }

    @Override
    public Object initPM() {
        return new ShopCartPM(this);
    }

    @Override
    public void click(View view) {

    }

    //跳入地址列表页面
    @Override
    public void goPay(List<Good> goods) {
        //TODO 跳入选择地址界面
        ToastUtil.show("所选商品 + "+ goods.size());
    }

    //关闭当前页面
    @Override
    public void gofinish() {
        finish();
    }
}
