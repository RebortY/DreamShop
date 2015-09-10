package com.dream.main.tabme.record;


import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseFragment;
import com.dream.views.uitra.MaterialPullRefresh;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 25:12
 * 我的元梦购已经结束的
 */

public class MyDreamRecordunFragment extends BaseFragment implements MyDreamRecordView{

	MyDreamRecordunFragmentPM fragmentPM;

	@Override
	public int getlayoutId() {
		return R.layout.fragment_my_dreamrecordun;
	}

	@Override
	public Object initPM() {
		if(fragmentPM == null){
			fragmentPM = new MyDreamRecordunFragmentPM(this);
		}
		return fragmentPM;
	}

	@Override
	public void stopRefresh(View view) {
		((MaterialPullRefresh)view).refreshComplete();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (DreamApplication.getApp().eventBus() != null) {
			DreamApplication.getApp().eventBus().unregister(this);
		}
	}
}