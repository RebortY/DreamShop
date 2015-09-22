package com.dream.main.shopcart;

import android.view.View;

import com.dream.bean.Good;

import java.util.ArrayList;

/**
 * @author yangll
 */
public interface ShopCartView {
    void click(View view);
    void goPay(ArrayList<Good> goods);
    void gofinish();//关闭购物车
    void showDelDialog(Good good);
}
