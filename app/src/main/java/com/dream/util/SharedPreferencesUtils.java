package com.dream.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/19 15:59
 */
public class SharedPreferencesUtils {

    private static String hand = "hand_img";
    private static String path =  "dream_shop_hand";

    // 存储sharedpreferences
    public static void setSharedPreference(Context mContext, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(path, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(hand, value);
        editor.commit();// 提交修改
    }

    // 清除sharedpreferences的数据
    public static void removeSharedPreference(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(path, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(hand);
        editor.commit();// 提交修改
    }

    // 获得sharedpreferences的数据
    public static String getSahrePreference(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(path, Context.MODE_PRIVATE);
        String path = sharedPreferences.getString(hand, "");
        return path;
    }
}
