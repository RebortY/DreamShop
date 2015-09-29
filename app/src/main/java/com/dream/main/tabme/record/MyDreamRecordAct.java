package com.dream.main.tabme.record;

import android.support.v4.app.Fragment;
import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseFragmentAct;
import com.dream.views.uitra.MaterialPullRefresh;

import java.util.ArrayList;
import java.util.List;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/8 22:12
 * 我的元梦购
 */
public class MyDreamRecordAct extends BaseFragmentAct{

    MyDreamRecordPM dreamRecordPM;

    @Override
    public int[] initTabText() {
        return new int[]{R.string.tv_my_dream_ing, R.string.tv_my_dream_un};
    }

    @Override
    public List<Fragment> initFrament() {
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new MyDreamRecordingFragment());
        list.add(new MyDreamRecordunFragment());
        return list;
    }

    @Override
    public int titleValue() {
        return R.string.tv_my_dream_shop;
    }


    @Override
    public Object initPM() {
        if(dreamRecordPM == null){
            dreamRecordPM = new MyDreamRecordPM();
        }
        return dreamRecordPM;
    }
}
