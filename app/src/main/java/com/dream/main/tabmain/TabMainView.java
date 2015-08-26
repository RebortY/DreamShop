package com.dream.main.tabmain;

import android.view.View;

import com.dream.main.tabmain.pmbeans.AbstractBean;

import java.util.List;

/**
 * Created by yangll on 15/8/22.
 */
public interface TabMainView {
    void setCarouselAdapter(List<Carousel> datas);
    void handlGoodsView(AbstractBean bean , View view);
    void showToast(String str);
}
