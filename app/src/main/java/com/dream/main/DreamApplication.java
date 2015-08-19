package com.dream.main;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.dream.db.DreamDB;
import com.dream.net.DreamNet;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.snowdream.android.util.Log;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

import control.EBEventBus;

/**
 * Created by yangll on 15/8/2.
 */
public class DreamApplication extends Application {

    private static DreamApplication app = null;

    /**
     * 事件总线
     * 用于发送数据在应用中传递
     */
    private static EBEventBus eventBus = null;

    /**
     * 数据库操作
     */
    private static DreamDB db = null;

    /**
     * mvvm 框架
     */
    BinderFactory bf = null;

    /**
     * 网络控制类
     */
    DreamNet dreamNet = null;

    final String TAG = "DREAMSHOP";

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        eventBus = EBEventBus.createEventBus(true);
        db = new DreamDB(getApplicationContext());
        bf = new BinderFactoryBuilder().build();
        dreamNet = new DreamNet(getApplicationContext());
        //初始化图片处理
        Fresco.initialize(getApplicationContext());

        Log.setEnabled(true);
        Log.setGlobalTag(TAG);
    }

    public DreamNet getDreamNet(){
        return dreamNet;
    }

    private ViewBinder getViewBinder(Context ctx){
        return bf.createViewBinder(ctx, true);
    }

    public View inflateViewAndBind(Context ctx ,int layoutId , Object pm){
        return getViewBinder(ctx).inflateAndBind(layoutId,pm);
    }

    public static DreamApplication getApp(){
        return app;
    }

    public  EBEventBus eventBus(){
        return eventBus;
    }

    public  DreamDB getdb(){
        return db;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
