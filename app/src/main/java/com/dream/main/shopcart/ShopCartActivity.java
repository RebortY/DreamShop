package com.dream.main.shopcart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.dream.R;
import com.dream.bean.Good;
import com.dream.main.base.BaseActivity;
import com.dream.main.goodpay.GoodPayActivity;
import com.dream.main.tabme.address.AddressActivity;
import com.dream.shopcart.ShopCart;
import com.dream.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author yangll
 */
public class ShopCartActivity extends BaseActivity implements ShopCartView {

    ShopCartPM pm = null;
    @Bind(R.id.right_btn)
    ImageButton rightBtn;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rightBtn.setImageDrawable(getResources().getDrawable(R.drawable.gongping));
    }

    @Override
    public void click(View view) {

    }

    //跳入地址列表页面
    @Override
    public void goPay(ArrayList<Good> goods) {
        //TODO 跳入选择地址界面
        if (goods.size() > 0) {
            Intent intent = new Intent(this, AddressActivity.class);
            intent.putParcelableArrayListExtra(GoodPayActivity.GOODLIST, goods);
            startActivity(intent);
        } else {
            ToastUtil.show("请选择商品");
        }

    }

    @Override
    public void showDelDialog(List<Good> good) {
        if (good == null || good.size() == 0)
            ToastUtil.show("请选择要删除商品");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认要删除吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Good> lists = ShopCart.getShopCart().getReadyPays();
                pm.removeGood(lists);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    //关闭当前页面
    @Override
    public void gofinish() {
        finish();
    }
}
