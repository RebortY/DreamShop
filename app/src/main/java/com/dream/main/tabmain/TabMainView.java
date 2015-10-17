package com.dream.main.tabmain;

import android.view.View;

import com.dream.bean.Good;
import com.dream.main.tabmain.pmbeans.AbstractBean;

import java.util.List;

/**
 * Created by yangll on 15/8/22.
 */
public interface TabMainView {
    void setCarouselAdapter(List<Carousel> datas);
    void handlGoodsView(AbstractBean bean , View view);
    void showToast(int strId);
    void stopRefresh(View view);
    void goGoodInfo(Good goodId);
    void gowebView(String url);
}
