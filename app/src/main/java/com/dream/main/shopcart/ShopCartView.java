package com.dream.main.shopcart;

import android.view.View;

import com.dream.bean.Good;

import java.util.List;

/**
 * @author yangll
 */
public interface ShopCartView {
    void click(View view);
    void goPay(List<Good> goods);
    void gofinish();//关闭购物车
}
