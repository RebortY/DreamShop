package com.dream.main.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/5 11:23
 */
public abstract class BaseArrayListAdapter<T> extends BaseAdapter {
    protected ArrayList<T> mList;// 泛型list
    protected Context mContext;


    public BaseArrayListAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 添加list，当然是第一位
     *
     * @param list
     */
    public void addFirst(ArrayList<T> list) {
        ArrayList<T> l = new ArrayList<T>();
        l.addAll(list);
        l.addAll(mList);
        this.mList.clear();
        this.mList = l;
        notifyDataSetChanged();
    }

    public void upData(ListView listView, int position) {
        View view = listView.getChildAt(position
                - listView.getFirstVisiblePosition());
        getView(position, view, listView);
    }

    public boolean isEmpty() {
        if (mList != null && mList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取数量
     */
    public int getCount() {

        return mList == null ? 0 : mList.size();
    }

    /**
     * 获取每个item
     */
    public T getItem(int position) {
        if (position >= getCount())
            return null;
        return mList == null ? null : mList.get(position);
    }

    /**
     * 获取item的position
     */
    public long getItemId(int position) {
        return position;
    }

    public int getItemId(T bean) {
        return mList.indexOf(bean);
    }

    /**
     * 根据position 移除item
     */
    public void removeItem(int position) {
        mList.remove(position);
        this.notifyDataSetChanged();
    }

    /**
     * 抽象方法，
     */
    @Override
    abstract public View getView(int position, View convertView,
                                 ViewGroup parent);

    /**
     * 直接增加到现有list
     *
     * @param list
     */
    public void addLists(ArrayList<T> list) {
        if (this.mList == null) {
            this.mList = list;
        } else {
            this.mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 直接增加到现有list
     *
     * @param list
     */
    public void addListsLast(ArrayList<T> list) {
        if (this.mList == null) {
            this.mList = list;
        } else {
            this.mList.addAll(this.mList.size(), list);
        }
        notifyDataSetChanged();
    }

    /**
     * set方法
     */
    public void setList(ArrayList<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        this.mList = list;
        this.notifyDataSetChanged();
    }

    /**
     * 获取当前list
     *
     * @return
     */
    public ArrayList<T> getList() {
        return mList;
    }

    /**
     * clear当前list
     */
    public void clear() {
        if (mList != null)
            mList.clear();
        notifyDataSetChanged();
    }

    /**
     * T［］添加到list集合
     *
     * @param list
     */
    public void setList(T[] list) {
        ArrayList<T> arrayList = new ArrayList<T>(list.length);
        for (T t : list) {
            arrayList.add(t);
        }
        setList(arrayList);
    }
}
