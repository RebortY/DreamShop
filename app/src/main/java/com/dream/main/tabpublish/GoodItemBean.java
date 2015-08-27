package com.dream.main.tabpublish;

import com.dream.bean.Good;
import com.dream.main.tabmain.pmbeans.AbstractBean;

/**
 * Created by yangll on 15/8/27.
 */
public class GoodItemBean extends AbstractBean {

    //正在揭晓是否显示
    private boolean jx_soon_visibility = true;

    public GoodItemBean(Good goodItem) {
        super(goodItem);
    }

}
