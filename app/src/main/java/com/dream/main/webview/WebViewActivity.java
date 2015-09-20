package com.dream.main.webview;

import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dream.R;
import com.dream.main.base.BaseActivity;

import butterknife.Bind;

/**
 * @author yangll
 */
public class WebViewActivity extends BaseActivity {

    String url;
    public final static String URI = "webview_url";
    @Bind(R.id.webview)
    WebView webview;

    @Override
    public void setAttIntent(Intent intent) {
        url = intent.getStringExtra(URI);
    }

    @Override
    public int getLayoutId() {
        return R.layout.webview;
    }

    @Override
    public void initView() {
        webview.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = webview.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        //设置WebView可触摸放大缩小
        settings.setBuiltInZoomControls(true);
        webview.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }else{
            finish();
        }
        return false;
    }

    @Override
    public Object initPM() {
        return new WebViewPM();
    }

}
