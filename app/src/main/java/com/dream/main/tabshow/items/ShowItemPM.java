package com.dream.main.tabshow.items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.tabshow.ShowView;
import com.dream.util.DreamUtils;
import com.dream.views.imageview.DreamImageView;
import com.facebook.drawee.drawable.ScalingUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;
import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;
import org.robobinding.widget.compoundbutton.CheckedChangeEvent;
import org.robobinding.widget.view.ClickEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangll on 15/8/31.
 */
public class ShowItemPM implements ItemPresentationModel<GoodForm>, HasPresentationModelChangeSupport {

    GoodForm goodForm = null;
    ShowView showView = null;

    private String url; //头像
    private boolean circle = true; //是否圆角
    private String name; //用户名
    private String des; //描述
    private String time; //晒单时间
    private boolean praisebox; //是否点赞

    PresentationModelChangeSupport changeSupport = new PresentationModelChangeSupport(this);

    //晒单中的 按钮
    public void onclick(ClickEvent event) {
        showView.onClick(event.getView(), goodForm);
    }

    public void onCheck(CheckedChangeEvent event) {
        goodForm.setParise(event.isChecked());
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

    public boolean isPraisebox() {
        return goodForm.isParise();
    }

    public void setPraisebox(boolean praisebox) {
        this.praisebox = praisebox;
        changeSupport.firePropertyChange("praisebox");
    }

    public ShowItemPM(ShowView view) {
        this.showView = view;
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
        } else {
            gv = viewHolder.getGv();
        }
        GBaseAdapter adpater = (GBaseAdapter) viewHolder.getGv().getAdapter();
        if (adpater == null) {
            adpater = new GBaseAdapter();
            adpater.setData(goodForm.getSd_photolist());
            gv.setAdapter(adpater);
        } else {
            gv.setNumColumns(goodForm.getSd_photolist().size() > 1 ? 3 : 1);
            adpater.setData(goodForm.getSd_photolist());
        }
        gv.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            showView.intentShowInfo(goodForm);
        });
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
                LayoutInflater inflater = showView.getLayoutInflater();
                convertView = inflater.inflate(R.layout.show_griditem, null);
                DreamImageView imgView = (DreamImageView) convertView.findViewById(R.id.dramImage);
                imgView.setUrl(urls.get(position));
                imgView.setAspectRatio(1.0f);
                convertView.setTag(imgView);
            } else {
                ((DreamImageView) convertView.getTag()).setUrl(urls.get(position));
            }
            if (getCount() == 1) {
                DreamImageView dim = (DreamImageView) convertView.getTag();
                dim.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);
                dim.setAspectRatio(1.33f);
            }
            return convertView;
        }
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }
}
