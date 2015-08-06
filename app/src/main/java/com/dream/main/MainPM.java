package com.dream.main;

import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.radiogroup.CheckedChangeEvent;

/**
 * Created by yangll on 15/8/5.
 * mian Activity 的 vm
 */
@PresentationModel
public class MainPM {

    MainLogicListener listener ;
    public MainPM(MainLogicListener callbacks) {
        this.listener = callbacks;
    }

    public void radioChange(CheckedChangeEvent event){
        int id = event.getCheckedId();
        listener.radioChange(id);
    }

}
