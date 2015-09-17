package com.dream.main.shopcart;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

/**
 * @author yangll
 */
@PresentationModel
public class ShopCartEmptyPM {

    private ShopCartView view;
    public ShopCartEmptyPM(ShopCartView view) {
        this.view = view;
    }

    public void gofinish(ClickEvent event){
        this.view.gofinish();
    }
}
