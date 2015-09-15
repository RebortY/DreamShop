package com.dream.shopcart;

import com.dream.bean.AuthUser;
import com.dream.bean.Good;
import com.dream.main.DreamApplication;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/9/15.
 * 购物车
 */
public class ShopCart {

    private static ShopCart shopCart = null;

    private ShopCart() {
        shopCart = new ShopCart();
    }

    public static ShopCart getShopCart(){
        return shopCart;
    }

    public boolean addShop(Good good){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return false;
        ShopBean bean = new ShopBean();
        bean.setUid(user.getUid());
        bean.setSid(good.getSid());
        DreamApplication.getApp().getdb().save(bean);
        return true;
    }

    public boolean removeShop(Good good){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return false;
        WhereBuilder builder = WhereBuilder.create();
        builder.equals("sid",good.getSid()).andEquals("uid", user.getUid());
        DreamApplication.getApp().getdb().delete(ShopBean.class, builder);
        return true;
    }

    //获取当前人员所有购物车数据
    public List<Good> getShopList(){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return null;
        List<ShopBean> beans = DreamApplication.getApp().getdb().query(QueryBuilder.create(ShopBean.class).whereEquals("uid",user.getUid()));
        List<Good> goods = new ArrayList<>();
        for(ShopBean bean : beans){
            Good good =  DreamApplication.getApp().getdb().queryById(bean.getSid(),Good.class);
            goods.add(good);
        }
        return goods;
    }


}
