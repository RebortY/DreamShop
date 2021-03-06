package com.dream.shopcart;

import com.dream.bean.AuthUser;
import com.dream.bean.Good;
import com.dream.bean.shopcart.ShopBean;
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
    private ArrayList<Good> readyPays = new ArrayList<>();

    private ShopCart() {

    }
    public static ShopCart getShopCart(){
        if(shopCart == null) shopCart = new ShopCart();
        return shopCart;
    }

    public boolean addShop(Good good){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return false;

        WhereBuilder builder = WhereBuilder.create();
        builder.equals("id",good.getId()).andEquals("uid", user.getUid());
        List<ShopBean> beans= DreamApplication.getApp().getdb().query(QueryBuilder.create(ShopBean.class).where(builder));
        if(beans != null && beans.size() > 0) return true;

        ShopBean bean = new ShopBean();
        bean.setUid(user.getUid());
        bean.setId(good.getId());
        DreamApplication.getApp().getdb().save(bean);
        return true;
    }

    public boolean removeShop(Good good){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return false;
        WhereBuilder builder = WhereBuilder.create();
        builder.equals("id",good.getId()).andEquals("uid", user.getUid());
        DreamApplication.getApp().getdb().delete(ShopBean.class, builder);
        removeReadyPay(good);
        return true;
    }

    public boolean removeShopList(List<Good> goods){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return false;
        if(goods == null || goods.size() ==0) return false;

        List<ShopBean> shops = new ArrayList<>(10);
        ArrayList<Good> tempGoods = new ArrayList<>();
        tempGoods.addAll(goods);
        for(Good g : tempGoods){
            ShopBean b = new ShopBean();
            b.setAutioId(g.getShopId());
            b.setId(g.getId());
            b.setUid(user.getUid());
            shops.add(b);
            removeReadyPay(g);
        }
        if(shops.size() > 0){
            DreamApplication.getApp().getdb().delete(shops);
        }

//        WhereBuilder builder = WhereBuilder.create();
//        for(Good good : goods){
//            DreamApplication.getApp().getdb().delete(ShopBean.class, builder);
//            removeReadyPay(good);
//        }
        return true;
    }

    //获取当前人员所有购物车数据
    public List<Good> getShopList(){
        AuthUser user =  DreamApplication.getApp().getUser();
        if(user == null || !user.isLogin()) return null;
        List<ShopBean> beans = DreamApplication.getApp().getdb().query(QueryBuilder.create(ShopBean.class).whereEquals("uid",user.getUid()));
        List<Good> goods = new ArrayList<>();
        for(ShopBean bean : beans){
            Good good =  DreamApplication.getApp().getdb().queryById(bean.getId(),Good.class);
            good.setShopId(bean.getAutioId());
            goods.add(good);
        }
        return goods;
    }

    //存放准备付款的商品
    public synchronized void addReadyPay(Good good){
        readyPays.add(good);
    }

    //删除准备付款的商品
    public  void removeReadyPay(Good good){
        if(readyPays.indexOf(good) >= 0)
            readyPays.remove(good);
    }

    public synchronized void removeReadyPayList(List<Good> good){
            readyPays.removeAll(good);
    }

    //获取准备支付的商品列表
    public ArrayList<Good> getReadyPays(){
        return readyPays;
    }
}
