package com.dream.main.goodpay;

import com.dream.bean.Good;
import com.dream.main.titlebar.TitleBarPM;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangll
 */
@PresentationModel
public class GoodPayPM extends TitleBarPM {

    List<Good> data = new ArrayList<Good>();
    GoodPayView view;
    public GoodPayPM(GoodPayView view) {
        this.view = view;
    }

    @ItemPresentationModel(value = GoodPayItemPM.class)
    public List<Good> getData() {
        return data;
    }

    public void setData(List<Good> data) {
        if(data != null && data.size() > 0)
            this.data.addAll(data);
        pmRefresh("data");
    }

    //跳支付界面
    public void gopay(ClickEvent event){
        view.gopay();
    }
}
