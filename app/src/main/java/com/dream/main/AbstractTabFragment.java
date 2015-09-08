package com.dream.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.main.tabmain.TabMainFragment;
import com.dream.main.tabme.MeFragment;
import com.dream.main.tabpublish.ShowPublishFragment;

/**
 * Created by yangll on 15/8/11.
 */
public abstract class AbstractTabFragment extends Fragment {

    public static final String EXTRA_PRODUCT_INDEX = "com.dream.tab";
    //主界面逻辑处理接口
    private MainLogicListener logicListener;
    public AbstractTabFragment() {
    }
    public static AbstractTabFragment newInstance(int productIndex , MainLogicListener listener){
        Bundle args = new Bundle();
        args.putInt(EXTRA_PRODUCT_INDEX, productIndex);
        AbstractTabFragment fragment = null;
        switch(productIndex){
            case 0 : //首页
                fragment = new TabMainFragment();
                break;
            case 1:  //揭晓
                fragment = new ShowPublishFragment();
                break;
            case 2:  //晒单
                fragment = new ShowFragment();
                break;
            case 3:  //我的
                fragment = new MeFragment();
                break;
        }
        fragment.setLogicListener(listener);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getCurFragment().inflateView();
    }

    public  View inflateView(){
        return DreamApplication.getApp().inflateViewAndBind(getActivity() , getlayoutId() , getPM());
    }

    //子类如果想调用 MainActivity 中的交互，调用此方法
    public MainLogicListener getLogicListener() {
        return logicListener;
    }

    public void setLogicListener(MainLogicListener logicListener) {
        this.logicListener = logicListener;
    }

    public  abstract  int getlayoutId();
    public  abstract  Object getPM();
    public  abstract  AbstractTabFragment getCurFragment();
}
