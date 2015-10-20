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
    private ArrayList<Good> goods = new ArrayList<>();
    private ShopCartEmptyPM emptyPM = null;
    private ShopCartView view;
    private boolean visib = false;

    public ShopCartPM(ShopCartView view) {
        setTitleBar("购物车");
        setRightVisibility(true);

        this.view = view;
        List<Good> cartGoods = ShopCart.getShopCart().getShopList();
        List<Good> readyPays = ShopCart.getShopCart().getReadyPays();
        if (cartGoods != null && !cartGoods.isEmpty()) {
            for(Good gg : cartGoods){
                for(Good g : readyPays){
                    if(gg.getId().equals(g.getId())){
                        int count = gg.getAddCount()+1;
                        gg.setAddCount(count);
                        gg.setCheck(true);
                    }
                }
                goods.add(gg);
            }
            if(readyPays != null ) readyPays.clear();
        }
        if(goods.size() > 0){
            visib = true;
            pmRefresh("visib");
        }
    }


    @Override
    public void onRightClick(ClickEvent event) {
        view.showDelDialog(ShopCart.getShopCart().getReadyPays());
    }

    public void removeGood(List<Good> gods){
        goods.removeAll(gods);
        if(goods.size() == 0){
            visib = false;
            pmRefresh("visib");
        }
        ShopCart.getShopCart().removeShopList(gods);
        ShopCart.getShopCart().removeReadyPayList(gods);
        pmRefresh("goods");
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

    @ItemPresentationModel(value = ShopCartItemPM.class ,factoryMethod = "createShopCart")
    public List<Good> getGoods() {
        return goods;
    }

    public ShopCartItemPM createShopCart(){
        return new ShopCartItemPM(view);
    }

    public ShopCartEmptyPM getEmptyPM() {
        return new ShopCartEmptyPM(view);
    }

    public void gopay(ClickEvent event){
        ArrayList<Good> goods = ShopCart.getShopCart().getReadyPays();
        view.goPay(goods);
    }

}
