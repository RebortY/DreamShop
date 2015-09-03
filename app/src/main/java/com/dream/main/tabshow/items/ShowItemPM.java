package com.dream.main.tabshow.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.tabshow.ShowView;
import com.dream.util.ToastUtil;
import com.dream.views.imageview.DreamImageView;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.robobinding.widget.view.ClickEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangll on 15/8/31.
 */
public class ShowItemPM implements ItemPresentationModel<GoodForm> {

    GoodForm goodForm = null;
    ShowView view = null;

    private String url ; //头像
    private boolean circle =true ; //是否圆角
    private String name ; //用户名
    private String des; //描述
    private String time; //晒单时间

    //晒单中的 按钮
    public void onclick(ClickEvent event){
        view.onClick(event.getView());
    }

    public String getUrl() {
        return goodForm.getThumb();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getName() {
        return goodForm.getSd_title();
    }

    public String getDes() {
        return goodForm.getSd_content();
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date(goodForm.getSd_time());
        return dateFormat.format(d);
    }

    /**
     * 点击图片
     * @param event
     */
    public void imgclick(ItemClickEvent event){
        ToastUtil.show("点图片了");
    }

    public ShowItemPM(ShowView view) {
        this.view = view;
    }

    @Override
    public void updateData(GoodForm goodForm, ItemContext itemContext) {
        this.goodForm = goodForm;
        ViewHolder viewHolder =  (ViewHolder)itemContext.getItemView().getTag();
        if(viewHolder == null){
            GridView gv =  (GridView)itemContext.getItemView().findViewById(R.id.showgridview);
            viewHolder = new ViewHolder(gv);
            itemContext.getItemView().setTag(viewHolder);
        }else{
            GBaseAdapter adpater =  (GBaseAdapter)viewHolder.getGv().getAdapter();
            if(adpater == null){
                viewHolder.getGv().setAdapter(new GBaseAdapter());
            }else{
                adpater.setData(goodForm.getSd_photolist());
            }
        }
    }

    class ViewHolder{
        GridView gv = null;
        public ViewHolder(GridView gv) {
            this.gv = gv;
        }

        public GridView getGv() {
            return gv;
        }
    }

    class GBaseAdapter extends android.widget.BaseAdapter{

        List<String> urls = null;
        public GBaseAdapter() {
            urls = new ArrayList<>();
        }

        public void setData(List<String> urls){
            this.urls.clear();
            this.urls.addAll(urls);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return goodForm.getSd_photolist().size();
        }

        @Override
        public Object getItem(int position) {
            return urls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater= view.getLayoutInflater();
                convertView = inflater.inflate(R.layout.show_griditem , null);
                DreamImageView imgView = (DreamImageView)convertView.findViewById(R.id.dramImage);
                imgView.setUrl(urls.get(position));
                convertView.setTag(imgView);
            }else{
                ((DreamImageView)convertView.getTag()).setUrl(urls.get(position));
            }
            return convertView;
        }
    }
}