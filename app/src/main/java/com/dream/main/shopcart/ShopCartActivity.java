package com.dream.main.shopcart;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.base.BaseActivity;
import com.dream.main.goodpay.GoodPayActivity;
import com.gc.materialdesign.widgets.Dialog;

import java.util.ArrayList;

/**
 * @author yangll
 */
public class ShopCartActivity extends BaseActivity implements ShopCartView{

    ShopCartPM pm = null;

    @Override
    public int getLayoutId() {
        return R.layout.shopcart;
    }

    @Override
    public Object initPM() {
        pm = new ShopCartPM(this);
        return pm;
    }

    @Override
    public void click(View view) {

    }

    //跳入地址列表页面
    @Override
    public void goPay(ArrayList<Good> goods) {
        //TODO 跳入选择地址界面
        Intent intent = new Intent(this , GoodPayActivity.class);
        intent.putParcelableArrayListExtra(GoodPayActivity.GOODLIST , goods);
        startActivity(intent);
    }

    @Override
    public void showDelDialog(Good good) {
        Dialog dialog = new Dialog(this , "购物车" ,"确定要删除该商品？");
        dialog.addCancelButton("取消", (view) -> {
            dialog.dismiss();
        });
        dialog.setOnAcceptButtonClickListener((view) -> {
            pm.removeGood(good);
        });
        dialog.show();
        dialog.getButtonAccept().setText("删除");
        dialog.getButtonAccept().setTextColor(getResources().getColor(R.color.listtab_on));
    }

    //关闭当前页面
    @Override
    public void gofinish() {
        finish();
    }
}
