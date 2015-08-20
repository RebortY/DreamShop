package com.dream.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

/**
 * Created by yangll on 15/8/20.
 * <p/>
 * SharedPreferences 工具类
 * <p/>
 * 使用方式
 * 保存
 * <p/>
 * SPUtils.setParam(this, "String", "Set-Cookie");
 * SPUtils.setParam(this, "int", 10);
 * SPUtils.setParam(this, "boolean", true);
 * SPUtils.setParam(this, "long", 100L);
 * SPUtils.setParam(this, "float", 1.1f);
 * <p/>
 * 获取
 * <p/>
 * SPUtils.getParam(TimerActivity.this, "String", "");                                                                                        SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
 * SPUtils.getParam(TimerActivity.this, "boolean", false);
 * SPUtils.getParam(TimerActivity.this, "long", 0L);
 * SPUtils.getParam(TimerActivity.this, "float", 0.0f);
 */
public class SPUtils {


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String name = "dream_shop";
    private int mode = Context.MODE_PRIVATE;
    private Context context;

    public SPUtils(Context context) {
        this.context = context;
        this.sp = context.getSharedPreferences(name, mode);
        this.editor = sp.edit();
    }

    /**
     * 创建一个工具类，默认打开名字为name的SharedPreferences实例
     *
     * @param context
     * @param name    唯一标识
     * @param mode    权限标识
     */
    public SPUtils(Context context, String name, int mode) {
        this.sp = context.getSharedPreferences(name, mode);
        this.editor = sp.edit();
    }

    /**
     * 添加信息到SharedPreferences
     *
     * @param map
     * @throws Exception
     */
    public void add(Map<String, Object> map) {
        Set<String> set = map.keySet();
        for (String key : set) {
            setParam(key, map.get(key));
        }
        editor.commit();
    }

    /**
     * 保存信息
     * @param key
     * @param value   只支持 常用数据类型
     */
    public void add(String key , Object value){
        setParam(key , value);
        editor.commit();
    }

    /**
     * 删除信息
     *
     * @throws Exception
     */
    public void deleteAll() throws Exception {
        editor.clear();
        editor.commit();
    }

    /**
     * 删除一条信息
     */
    public void delete(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    private void setParam(String key, Object object) {
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getParam(String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 获取此SharedPreferences的Editor实例
     *
     * @return
     */
    private SharedPreferences.Editor getEditor() {
        return editor;
    }

    /**
     * 检查是否有保存的Key
     * @param key
     * @return
     */
    public boolean hasKey(final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key);
    }

}
