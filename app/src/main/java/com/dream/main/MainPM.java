package com.dream.main;

import com.dream.views.AbstractPM;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.radiogroup.CheckedChangeEvent;

/**
 * Created by yangll on 15/8/5.
 * mian Activity 的 vm
 */
@PresentationModel
public class MainPM extends AbstractPM{

    MainLogicListener listener ;
    public MainPM(MainLogicListener callbacks) {
        this.listener = callbacks;
    }

    public void radioChange(CheckedChangeEvent event){
        int id = event.getCheckedId();
        listener.radioChange(id);
    }

}
