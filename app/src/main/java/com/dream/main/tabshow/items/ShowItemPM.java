package com.dream.main.tabshow.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.tabshow.ShowView;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.dream.views.imageview.DreamImageView;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.adapterview.ItemClickEvent;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/8/31.
 */
public class ShowItemPM implements ItemPresentationModel<GoodForm> , HasPresentationModelChangeSupport{

    GoodForm goodForm = null;
    ShowView view = null;

    private String url; //头像
    private boolean circle = true; //是否圆角
    private String name; //用户名
    private String des; //描述
    private String time; //晒单时间
    private String praise = "点赞";

    PresentationModelChangeSupport changeSupport = new PresentationModelChangeSupport(this);

    //晒单中的 按钮
    public void onclick(ClickEvent event) {
        if (event.getView().getId() == R.id.praise) { //点赞
            goodForm.setParise(!goodForm.isParise());
            changeSupport.firePropertyChange("praise");
        }else{
            view.onClick(event.getView(), goodForm);
        }
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
        return DreamUtils.formatSecTime(goodForm.getTime(), "yyyy-MM-dd");
    }

    public String getPraise() {
        return goodForm.isParise() ? "取消赞" : "点赞";
    }

    /**
     * 点击图片
     *
     * @param event
     */
    public void imgclick(ItemClickEvent event) {
        ToastUtil.show("点图片了");
    }

    public ShowItemPM(ShowView view) {
        this.view = view;
    }

    @Override
    public void updateData(GoodForm goodForm, ItemContext itemContext) {
        this.goodForm = goodForm;
        ViewHolder viewHolder = (ViewHolder) itemContext.getItemView().getTag(R.id.showgridview);
        GridView gv = null;
        if (viewHolder == null) {
            gv = (GridView) itemContext.getItemView().findViewById(R.id.showgridview);
            viewHolder = new ViewHolder(gv);
            itemContext.getItemView().setTag(R.id.showgridview, viewHolder);
        }
        GBaseAdapter adpater = (GBaseAdapter) viewHolder.getGv().getAdapter();
        if (adpater == null) {
            adpater = new GBaseAdapter();
            adpater.setData(goodForm.getSd_photolist());
            viewHolder.getGv().setAdapter(adpater);
        } else {
            adpater.setData(goodForm.getSd_photolist());
        }
        adpater.notifyDataSetChanged();
    }

    class ViewHolder {
        GridView gv = null;

        public ViewHolder(GridView gv) {
            this.gv = gv;
        }

        public GridView getGv() {
            return gv;
        }
    }

    class GBaseAdapter extends android.widget.BaseAdapter {

        List<String> urls = null;

        public GBaseAdapter() {
            urls = new ArrayList<>();
        }

        public void setData(List<String> urls) {
            this.urls.clear();
            this.urls.addAll(urls);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return urls.size();
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
            if (convertView == null) {
                LayoutInflater inflater = view.getLayoutInflater();
                convertView = inflater.inflate(R.layout.show_griditem, null);
                DreamImageView imgView = (DreamImageView) convertView.findViewById(R.id.dramImage);
                imgView.setUrl(urls.get(position));
                convertView.setTag(imgView);
            } else {
                ((DreamImageView) convertView.getTag()).setUrl(urls.get(position));
            }
            return convertView;
        }
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
