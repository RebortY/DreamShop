package com.dream.main.infoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.dream.R;
import com.dream.bean.goodinfo.QishulistEntity;
import com.dream.bean.goodinfo.RecordsEntity;
import com.dream.main.DreamApplication;
import com.dream.main.infoview.canyu.BenqiJoinActivity;
import com.dream.main.infoview.jiexiao.JiexiaoActivity;
import com.dream.main.shopcart.ShopCartActivity;
import com.dream.main.webview.WebViewActivity;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.layout.LayoutItem;
import com.dream.views.uitra.MaterialPullRefresh;

import java.text.MessageFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangll on 15/9/4.
 * 商品详细页
 */
public class GoodInfoActivity extends FragmentActivity implements GoodInfoView {

    GoodInfoPM pm = null;
    public static final String GOODID = "GOODid";
    String goodId;
    @Bind(R.id.join)
    LayoutItem join;
    FragmentTransaction ft = null;
    public static final int jiexiao = 1, jinxingzhong = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String goodid = getIntent().getStringExtra(GOODID);
        pm = new GoodInfoPM(this);
        pm.setGood(goodid);
        View view = DreamApplication.getApp().inflateViewAndBind(this, R.layout.goodinfo, pm);
        setContentView(view);
        ButterKnife.bind(this);
    }

    public void replaceForState(int type) {
        ft = getSupportFragmentManager().beginTransaction();

        switch (type) {
            case jiexiao:
                JieXiaoFragment jfragment = new JieXiaoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(JieXiaoFragment.GOODID,pm.getGoodInfo());
                jfragment.setArguments(bundle);
                ft.replace(R.id.container, jfragment);
                break;
            case jinxingzhong:
                GoingFragment fragment = new GoingFragment();
                bundle = new Bundle();
                bundle.putSerializable(GoingFragment.GOODID,pm.getGoodInfo());
                fragment.setArguments(bundle);
                ft.replace(R.id.container,fragment);
                break;
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.join: //我本期参与的奖品

                break;
            case R.id.tuwen: //图文详情
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.URI, MessageFormat.format(ProtocolUrl.tuwenInfo, pm.getGoodInfo().getId()));
                startActivity(intent);
                break;
            case R.id.jiexiao: //往期揭晓
                intent = new Intent(this, JiexiaoActivity.class);
                ArrayList<QishulistEntity> qishulist = pm.getGoodInfo().getQishulist();
                if (qishulist == null) {
                    ToastUtil.show("暂没有往期期数");
                    return;
                }
                intent.putParcelableArrayListExtra(JiexiaoActivity.JIEXIAOLIST, qishulist);
                startActivity(intent);
                break;
            case R.id.shaidanfenxiang: //晒单分享
                break;
            case R.id.canyujilu: //本期所有参与记录
                intent = new Intent(this, BenqiJoinActivity.class);
                ArrayList<RecordsEntity> records = pm.getGoodInfo().getRecords();
                if (records == null) {
                    ToastUtil.show("本期暂无参与记录");
                    return;
                }
                intent.putParcelableArrayListExtra(BenqiJoinActivity.CANYU, records);
                startActivity(intent);
                break;
            case R.id.canyu:
                intent = new Intent(this, ShopCartActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setCanyuTextCount(int count) {
        if (join != null)
            join.setText(getResources().getString(R.string.goodInfo_join, count));
    }
}
