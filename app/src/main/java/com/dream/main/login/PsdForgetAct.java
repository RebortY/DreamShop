package com.dream.main.login;

import android.view.View;

import com.dream.R;
import com.dream.main.base.BaseActView;
import com.dream.main.base.BaseActivity;

import butterknife.Bind;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/30 11:35
 * 忘记密码
 */
public class PsdForgetAct  extends BaseActivity implements PsdForgetView {

    @Bind(R.id.layout_view)
            View layoutView;

    PsdForgetPM psdForgetPM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_psd_forget;
    }

    @Override
    public Object initPM() {
        if(psdForgetPM == null){
            psdForgetPM = new PsdForgetPM(this, this);
        }
        return psdForgetPM;
    }

    @Override
    public void setOnClickView(View view) {

    }

    @Override
    public void setOnActClick(int type) {
        if(type == 1){
            finish();
        }
        if(type == 2){
            layoutView.setVisibility(View.VISIBLE);
        }

    }
}
