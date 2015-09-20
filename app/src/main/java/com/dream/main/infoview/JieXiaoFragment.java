package com.dream.main.infoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;
import com.dream.bean.goodinfo.GoodInfo;
import com.dream.main.DreamApplication;
import com.dream.main.webview.WebViewActivity;
import com.dream.net.business.ProtocolUrl;

import java.text.MessageFormat;

/**
 * @author yangll
 */
public class JieXiaoFragment extends Fragment implements JieXiaoView {

    GoodInfo goodInfo;
    public static final String GOODID = "GOING_GOOD";

    public JieXiaoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodInfo = (GoodInfo) getArguments().getSerializable(GOODID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        JieXiaoFragmentPM jxp = new JieXiaoFragmentPM(goodInfo);
        jxp.setView(this);
        View view = DreamApplication.getApp().inflateViewAndBind(getActivity(), R.layout.jiexiaofragment, jxp);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        goodInfo = null;
    }

    @Override
    public void gotoInfo(GoodInfo info) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URI, MessageFormat.format(ProtocolUrl.countResult, goodInfo.getId()));
        startActivity(intent);
    }
}
