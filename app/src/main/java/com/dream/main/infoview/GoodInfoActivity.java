package com.dream.main.infoview;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.goodinfo.QishulistEntity;
import com.dream.bean.goodinfo.RecordsEntity;
import com.dream.main.base.BaseActivity;
import com.dream.main.infoview.canyu.BenqiJoinActivity;
import com.dream.main.infoview.jiexiao.JiexiaoActivity;
import com.dream.util.ToastUtil;
import com.dream.views.layout.LayoutItem;
import com.dream.views.uitra.MaterialPullRefresh;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by yangll on 15/9/4.
 * 商品详细页
 */
public class GoodInfoActivity extends BaseActivity implements GoodInfoView {

    GoodInfoPM pm = null;
    public static final String GOODID = "GOODid";
    String goodId;
    @Bind(R.id.join)
    LayoutItem join;


    @Override
    public void setAttIntent(Intent intent) {
        goodId = intent.getStringExtra(GOODID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.goodinfo;
    }

    @Override
    public Object initPM() {
        pm = new GoodInfoPM(this);
        pm.setGood(goodId);
        return pm;
    }

    @Override
    public void stopRefresh(View view) {
        ((MaterialPullRefresh) view).refreshComplete();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.join: //我本期参与的奖品

                break;
            case R.id.jiexiao: //往期揭晓
                Intent intent = new Intent(this , JiexiaoActivity.class);
                ArrayList<QishulistEntity> qishulist = pm.getGoodInfo().getQishulist();
                if(qishulist == null){
                    ToastUtil.show("暂没有往期期数");
                    return;
                }
                intent.putParcelableArrayListExtra(JiexiaoActivity.JIEXIAOLIST,qishulist);
                startActivity(intent);
                break;
            case R.id.shaidanfenxiang: //晒单分享
                break;
            case R.id.canyujilu: //本期所有参与记录
                intent = new Intent(this , BenqiJoinActivity.class);
                ArrayList<RecordsEntity> records = pm.getGoodInfo().getRecords();
                if(records == null){
                    ToastUtil.show("本期暂无参与记录");
                    return;
                }
                intent.putParcelableArrayListExtra(BenqiJoinActivity.CANYU, records);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setCanyuTextCount(int count) {
        if(join != null)
            join.setText(getResources().getString(R.string.goodInfo_join,count));
    }
}
