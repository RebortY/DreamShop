package com.dream.util;


import android.content.res.Resources;
import android.widget.Toast;

import com.dream.main.DreamApplication;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/26 20:18
 */
public class ToastUtil {


    public static void show( String text) {
        Toast mToast = Toast.makeText(DreamApplication.getApp(), text, Toast.LENGTH_SHORT);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
    public static void show( int id) {
        Resources res = DreamApplication.getApp().getResources();
        Toast mToast = Toast.makeText(DreamApplication.getApp(), res.getString(id), Toast.LENGTH_SHORT);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }
}
