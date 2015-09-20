package com.dream.main.infoview.showgood;

import android.content.Intent;

import com.dream.R;
import com.dream.bean.GoodForm;
import com.dream.main.base.BaseActivity;
import com.dream.main.webview.WebViewActivity;
import com.dream.net.business.ProtocolUrl;

import java.text.MessageFormat;

/**
 * Created by yangll on 15/9/8.
 * 晒单详情
 */
public class ShowInfoActivity extends BaseActivity implements ShowInfoView{

    public static final String  GOODFORM = "SHOWGOODFORM";
    private GoodForm good = null;
    ShowInfoPM pm = null;

    @Override
    public void setAttIntent(Intent intent) {
        good = (GoodForm)intent.getSerializableExtra(GOODFORM);
    }

    @Override
    public Object initPM()
    {
        pm = new ShowInfoPM(this);
        pm.setGood(good);
        return pm;
    }

    @Override
    public void goCountResult() {
        Intent intent = new Intent(this , WebViewActivity.class);
        intent.putExtra(WebViewActivity.URI, MessageFormat.format(ProtocolUrl.countResult,good.getSid()));
        startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.showinfolayout;
    }

}
