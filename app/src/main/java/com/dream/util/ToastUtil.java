package com.dream.util;


import android.content.Context;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/8/26 20:18
 */
public class ToastUtil {

    private static Toast mToast = null;


    public static void show(Context context, int id) {
        if (mToast == null) {
            mToast = Toast.makeText(context, context.getResources().getString(id), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(context.getResources().getString(id));
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }

    public static void show(Context context, String str) {
        if (mToast == null) {
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }
}
