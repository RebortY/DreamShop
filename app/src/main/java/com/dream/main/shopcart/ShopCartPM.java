package com.dream.main.shopcart;

import com.dream.bean.Good;
import com.dream.main.titlebar.TitleBarPM;
import com.dream.shopcart.ShopCart;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangll
 */
@PresentationModel
public class ShopCartPM extends TitleBarPM{
    private boolean loadEnable = false;
    private List<Good> goods = new ArrayList<>();
    private ShopCartEmptyPM emptyPM = null;
    private ShopCartView view;
    private boolean visib = false;

    public ShopCartPM(ShopCartView view) {
        setTitleBar("购物车");
        this.view = view;
        List<Good> cartGoods = ShopCart.getShopCart().getShopList();
        if (cartGoods != null && !cartGoods.isEmpty()) {
            goods.addAll(cartGoods);
        }
        if(goods.size() > 0){
            visib = true;
            pmRefresh("visib");
        }
    }

    public boolean isVisib() {
        return visib;
    }

    public void setVisib(boolean visib) {
        this.visib = visib;
    }

    public boolean isLoadEnable() {
        return loadEnable;
    }

    @ItemPresentationModel(value = ShopCartItemPM.class)
    public List<Good> getGoods() {
        return goods;
    }

    public ShopCartEmptyPM getEmptyPM() {
        return new ShopCartEmptyPM(view);
    }

    public void gopay(ClickEvent event){
        List<Good> goods = ShopCart.getShopCart().getReadyPays();
        view.goPay(goods);
    }

}
