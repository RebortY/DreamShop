package com.dream.main.tabme;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.dream.R;
import com.dream.bean.AddressEditBean;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.DreamUtils;
import com.dream.util.ToastUtil;
import com.dream.views.layout.LayoutItemEdit;

import java.util.HashMap;

import butterknife.Bind;
import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/4 20:20
 */
public class AddressEditAct extends BaseActivity implements BaseActView {

    private final String CODE_COMMIT_ADDRESS = "CODE_COMMIT_ADDRESS";//提交tag
    private final String CODE_SET_ADDRESS_DEF = "CODE_SET_ADDRESS_DEF";//设置默认
    public static final String CODE_RESULT_EDIT = "CODE_RESULT_EDIT";//提交完成post tag

    @Bind(R.id.address_name)
    LayoutItemEdit layoutName;

    @Bind(R.id.address_mobile)
    LayoutItemEdit layoutMobile;

    @Bind(R.id.address_sheng)
    LayoutItemEdit layoutSheng;

    @Bind(R.id.address_shi)
    LayoutItemEdit layoutShi;

    @Bind(R.id.address_xian)
    LayoutItemEdit layoutXian;

    @Bind(R.id.address_detail)
    LayoutItemEdit layoutDetail;

    @Bind(R.id.checkBox)
    CheckBox checkBox;


    AddressEditPM addressEditPM;

    AddressEditBean addressEditBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_edit;
    }

    @Override
    public Object initPM() {
        if (addressEditPM == null) {
            addressEditPM = new AddressEditPM(this);
        }
        return addressEditPM;
    }

    @Override
    public void initView() {
        DreamApplication.getApp().eventBus().register(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            addressEditBean = (AddressEditBean) bundle.getSerializable(AddressAct.class.getName());
            setText();
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }

    @Override
    public void setOnClickView(View view) {
        switch (view.getId()) {

            case R.id.bt_commit:
                if (isCheckText()) {
                    commitAddress();
                } else {
                    ToastUtil.show(R.string.tv_address_empty);
                }
                if (checkBox.isChecked()) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("id", addressEditBean.getId());
                    DreamApplication.getApp().getDreamNet().netJsonPost(CODE_SET_ADDRESS_DEF, ProtocolUrl.ADDRESS_LIST_DEF, map);
                }
                break;
        }
    }

    private void setText() {
        if (addressEditBean != null) {
            layoutName.setEditTextValue(addressEditBean.getShouhuoren());
            layoutMobile.setEditTextValue(addressEditBean.getMobile());
            layoutSheng.setEditTextValue(addressEditBean.getSheng());
            layoutShi.setEditTextValue(addressEditBean.getShi());
            layoutXian.setEditTextValue(addressEditBean.getXian());
            layoutDetail.setEditTextValue(addressEditBean.getJiedao());
        }
    }


    private void commitAddress() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sheng", layoutSheng.getEditTextValue());
        map.put("shi", layoutShi.getEditTextValue());
        map.put("xian", layoutXian.getEditTextValue());
        map.put("jiedao", layoutDetail.getEditTextValue());
        map.put("shouhuoren", layoutName.getEditTextValue());
        map.put("mobile", layoutMobile.getEditTextValue());
        map.put("defaulted", "Y");

        if (addressEditBean == null) {
            DreamApplication.getApp().getDreamNet().netJsonPost(CODE_COMMIT_ADDRESS, ProtocolUrl.ADDRESS_LIST_ADD, map);
        } else {
            map.put("id", addressEditBean.getId());
            DreamApplication.getApp().getDreamNet().netJsonPost(CODE_COMMIT_ADDRESS, ProtocolUrl.ADDRESS_LIST_UPDATA, map);
        }

    }

    @Subcriber(tag = CODE_COMMIT_ADDRESS, threadMode = ThreadMode.MainThread)
    public void respHandler(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            DreamApplication.getApp().eventBus().post(CODE_RESULT_EDIT, CODE_RESULT_EDIT);
            finish();
        } else {
            ToastUtil.show(R.string.net_error);
        }
    }

    @Subcriber(tag = CODE_SET_ADDRESS_DEF, threadMode = ThreadMode.MainThread)
    public void respHandleDef(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
        } else {
            ToastUtil.show(R.string.tv_address_def_empty);
        }
    }

    private boolean isCheckText() {

        if(DreamUtils.isEmpty(layoutSheng.getEditTextValue()) ||
                DreamUtils.isEmpty(layoutShi.getEditTextValue()) ||
                DreamUtils.isEmpty(layoutXian.getEditTextValue()) ||
                DreamUtils.isEmpty(layoutDetail.getEditTextValue()) ||
                DreamUtils.isEmpty(layoutName.getEditTextValue()) ||
                DreamUtils.isEmpty(layoutMobile.getEditTextValue())){
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (DreamApplication.getApp().eventBus() != null) {
            DreamApplication.getApp().eventBus().unregister(this);
        }
    }
}
