package com.dream.main.tabpublish;

import com.dream.R;
import com.dream.main.AbstractTabFragment;
import com.dream.main.DreamApplication;
import com.dream.main.MainPM;
import com.dream.main.tabmain.TabMainPM;

import org.robobinding.annotation.PresentationModel;

/**
 * Created by yangll on 15/8/16.
 * 揭晓
 */
public class ShowPublishFragment extends AbstractTabFragment{

    @Override
    public int getlayoutId() {
        return R.layout.fragment_publish;
    }

    @Override
    public Object getPM() {
        return new PublishPM();
    }

    @Override
    public AbstractTabFragment getCurFragment() {
        return this;
    }
}
