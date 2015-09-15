package com.dream.main.tabme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.R;
import com.dream.bean.AddressEditBean;
import com.dream.bean.AddressListItemInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.main.base.BaseArrayListAdapter;
import com.dream.views.layout.LayoutItemEdit;

import butterknife.Bind;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/4 14:26
 * 收货地址列表
 */
public class AddressAct extends BaseActivity implements BaseActView {

    public static final String ADDRESS_ITEM = "ADDRESS_ITEM";

    @Bind(R.id.listView_address)
    ListView listView;

    AddressPM addressPM;

    AddressAdapter adapter;

    @Override
    public void setOnClickView(View view) {

        adapter.addLists(addressPM.getAdressList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public Object initPM() {
        if (addressPM == null) {
            addressPM = new AddressPM(this, this, adapter);
        }
        return addressPM;
    }

    @Override
    public void initView() {

        adapter = new AddressAdapter(this);
        listView.setAdapter(adapter);
    }

    class AddressAdapter extends BaseArrayListAdapter {

        public AddressAdapter(Context context) {
            super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.address_list_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final AddressListItemInfo.DataEntity.ListEntity bean = (AddressListItemInfo.DataEntity.ListEntity) mList.get(position);

            viewHolder.addresstitle.setText(getResources().getString(R.string.tv_address_title, String.valueOf(position + 1)));
            viewHolder.addressname.setEditTextValue(bean.getShouhuoren());
            viewHolder.addressmobile.setEditTextValue(bean.getMobile());
            viewHolder.addresssheng.setEditTextValue(bean.getSheng());
            viewHolder.addressshi.setEditTextValue(bean.getShi());
            viewHolder.addressxian.setEditTextValue(bean.getXian());
            viewHolder.addressdetail.setEditTextValue(bean.getJiedao());
            viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AddressEditBean editBean = new AddressEditBean();
                    editBean.setId(bean.getId());
                    editBean.setShouhuoren(bean.getShouhuoren());
                    editBean.setSheng(bean.getSheng());
                    editBean.setShi(bean.getShi());
                    editBean.setXian(bean.getXian());
                    editBean.setJiedao(bean.getJiedao());
                    editBean.setMobile(bean.getMobile());

                    Intent intent = new Intent(mContext, AddressEditAct.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AddressAct.this.getClass().getName(), editBean);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

            return convertView;
        }

        public class ViewHolder {
            public final TextView addresstitle;
            public final ImageView imgedit;
            public final RelativeLayout layouttitle;
            public final LayoutItemEdit addressname;
            public final LayoutItemEdit addressmobile;
            public final LayoutItemEdit addresssheng;
            public final LayoutItemEdit addressshi;
            public final LayoutItemEdit addressxian;
            public final LayoutItemEdit addressdetail;
            public final View root;

            public ViewHolder(View root) {
                addresstitle = (TextView) root.findViewById(R.id.address_title);
                imgedit = (ImageView) root.findViewById(R.id.img_edit);
                layouttitle = (RelativeLayout) root.findViewById(R.id.layout_title);
                addressname = (LayoutItemEdit) root.findViewById(R.id.address_name);
                addressmobile = (LayoutItemEdit) root.findViewById(R.id.address_mobile);
                addresssheng = (LayoutItemEdit) root.findViewById(R.id.address_sheng);
                addressshi = (LayoutItemEdit) root.findViewById(R.id.address_shi);
                addressxian = (LayoutItemEdit) root.findViewById(R.id.address_xian);
                addressdetail = (LayoutItemEdit) root.findViewById(R.id.address_detail);
                this.root = root;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }
}
