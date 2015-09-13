package com.dream.main.seach;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dream.R;
import com.dream.bean.SeachGood;
import com.dream.main.base.BaseActivity;
import com.dream.views.imageview.DreamImageView;
import com.dream.views.progressbar.XProgressBar;
import com.paging.gridview.PagingBaseAdapter;
import com.paging.gridview.PagingGridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangll on 15/9/9.
 */
public class SeachActivity extends BaseActivity implements SeachView {

    @Bind(R.id.seach_gridview)
    PagingGridView seachGridview;
    GoodAdapter adpater;
    static final String SEACH_SET_TAG = "seach_set_data";
    static final String SEACH_ADD_TAG = "seach_add_data";

    @Override
    public int getLayoutId() {
        return R.layout.seachlayout;
    }

    @Override
    public Object initPM() {
        return new SeachPM(this);
    }

    @Override
    public void setData(Result result) {
        if (result.getGoods() == null) return;
        adpater.addMoreItems(result.getGoods());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        adpater = new GoodAdapter();
        seachGridview.setAdapter(adpater);
        seachGridview.setHasMoreItems(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class GoodAdapter extends PagingBaseAdapter<SeachGood> {

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void addMoreItems(List<SeachGood> newItems) {
            super.addMoreItems(newItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder vh = null;
            SeachGood good = (SeachGood) getItem(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.main_goods_item, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.goodimg.setUrl(good.getThumb() == null ? "res://R.drawable.ic_launcher" : good.getThumb());
            vh.goodtitle.setText(good.getTitle());
            vh.money.setText(good.getMoney() + "");
            vh.progressDeterminate.setMax(good.getCanyurenshu());
            vh.progressDeterminate.setProgress(good.getShenyurenshu());
            return convertView;
        }


        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'main_goods_item.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        class ViewHolder {
            @Bind(R.id.goodimg)
            DreamImageView goodimg;
            @Bind(R.id.goodtitle)
            TextView goodtitle;
            @Bind(R.id.money)
            TextView money;
            @Bind(R.id.progressDeterminate)
            XProgressBar progressDeterminate;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
