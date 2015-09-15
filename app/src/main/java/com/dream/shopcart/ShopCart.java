package com.dream.shopcart;

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

    }

}
