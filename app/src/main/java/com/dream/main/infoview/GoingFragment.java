package com.dream.main.infoview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.R;
import com.dream.bean.goodinfo.GoodInfo;
import com.dream.main.DreamApplication;

/**
 * @author yangll
 */
public class GoingFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String GOODID = "GOING_GOOD";
    GoodInfo info;
    public GoingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = (GoodInfo)getArguments().getSerializable(GOODID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  DreamApplication.getApp().inflateViewAndBind(getActivity(),R.layout.fragment_going , new GoingFragmentPM(info));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        info = null;
    }

}
