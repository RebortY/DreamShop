package com.dream.main;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.dream.bean.AuthUser;
import com.dream.db.DreamDB;
import com.dream.db.SPUtils;
import com.dream.net.DreamNet;
import com.dream.views.imageview.DreamImageView;
import com.dream.views.imageview.DreamImageViewBinding;
import com.dream.views.pulltorefresh.PullToRefreshAdapterViewBinding;
import com.dream.views.uitra.MaterialPullRefresh;
import com.dream.views.uitra.MaterialPullRefreshVB;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.snowdream.android.util.Log;
import com.litesuits.orm.db.DataBase;
import com.slib.pulltoviews.PullToRefreshAdapterViewBase;
import com.slib.pulltoviews.PullToRefreshListView;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

import java.lang.reflect.Method;

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

    /**
     * SharedPreferences 操作类
     */
    SPUtils spUtils = null;

    final String TAG = "DREAMSHOP";

    /**
     * 登录成功后的用户
     */
    AuthUser user = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        eventBus = EBEventBus.createEventBus(true);
        db = new DreamDB(getApplicationContext());
        dreamNet = new DreamNet(getApplicationContext());
        spUtils = new SPUtils(getApplicationContext());

        //初始化自定义绑定视图

        bf = new BinderFactoryBuilder()
                .add(new DreamImageViewBinding().forView(DreamImageView.class))
                .add(new MaterialPullRefreshVB().forView(MaterialPullRefresh.class))
                .add(new PullToRefreshAdapterViewBinding().forView(PullToRefreshAdapterViewBase.class))
                .build();

        //初始化图片处理
        Fresco.initialize(getApplicationContext());

        Log.setEnabled(true);
        Log.setGlobalTag(TAG);

    }

    public DreamNet getDreamNet() {
        return dreamNet;
    }

    private ViewBinder getViewBinder(Context ctx) {

        return bf.createViewBinder(ctx, true);
    }

    public View inflateViewAndBind(Context ctx, int layoutId, Object pm) {
        return getViewBinder(ctx).inflateAndBind(layoutId, pm);
    }

    public static DreamApplication getApp() {
        return app;
    }

    public EBEventBus eventBus() {
        return eventBus;
    }

    public DataBase getdb() {
        return db.getdb();
    }

    public AuthUser getUser() {
        return user;
    }

    public void setAuthUser(AuthUser authUser) {
        user = authUser;
    }

    public SPUtils getSharedPreferences() {
        return spUtils;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
