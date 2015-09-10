package com.dream.main.tabme.record;

import android.view.View;

import com.dream.R;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseFragment;
import com.dream.views.uitra.MaterialPullRefresh;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 22:12
 * 我的元梦购正在进行的
 */

public class MyDreamRecordingFragment extends BaseFragment implements MyDreamRecordView{

	MyDreamRecordingFragmentPM fragmentPM;

	@Override
	public int getlayoutId() {
		return R.layout.fragment_my_dream_record_ing;
	}

	@Override
	public Object initPM() {
		if(fragmentPM == null){
			fragmentPM = new MyDreamRecordingFragmentPM(this);
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