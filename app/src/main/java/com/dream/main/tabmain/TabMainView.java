package com.dream.main.tabmain;

import android.view.View;

import com.dream.main.tabmain.pmbeans.AbstractBean;
import com.dream.views.uitra.MaterialPullRefresh;

import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by yangll on 15/8/22.
 */
public interface TabMainView {
    void setCarouselAdapter(List<Carousel> datas);
    void handlGoodsView(AbstractBean bean , View view);
    void showToast(int strId);
    void stopRefresh(View view);
}
