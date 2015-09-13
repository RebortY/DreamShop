package com.dream.main.seach;

import com.dream.bean.Category;
import com.dream.main.DreamApplication;
import com.dream.util.ToastUtil;

import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.adapterview.ItemClickEvent;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by yangll on 15/9/9.
 */
@PresentationModel
public class SeachEmptyPM implements HasPresentationModelChangeSupport{

    private List<String> histroySeach = new ArrayList<>();
    PresentationModelChangeSupport changeSupport = null;

    public SeachEmptyPM() {
        changeSupport = new PresentationModelChangeSupport(this);
        Observable.just(histroySeach)
                .flatMap(categorys -> Observable.from(DreamApplication.getApp().getdb().queryAll(Category.class)))
                .flatMap(category -> addData(category))
                .subscribe(histroySeach -> changeSupport.firePropertyChange("histroySeach"));
    }

    private Observable<List<String>> addData(Category category){
        histroySeach.add(category.getName());
        return Observable.just(histroySeach);
    }

    @ItemPresentationModel(value = SeachEmptyItemPM.class)
    public List<String> getHistroySeach() {
        return histroySeach;
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public void clickItem(ItemClickEvent event){
        ToastUtil.show(event.getParent().getAdapter().getItem(event.getPosition()).toString());
    }

}
