package com.dream.main.tabme.record;

import android.content.Intent;
import android.view.View;

import com.dream.R;
import com.dream.bean.MyDreamRecordUnInfo;
import com.dream.bean.MyDreamRecordingInfo;
import com.dream.main.DreamApplication;
import com.dream.main.base.BaseFragment;
import com.dream.main.infoview.GoodInfoActivity;
import com.dream.views.uitra.MaterialPullRefresh;
import com.slib.pulltoviews.xviews.widget.XListView;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/9 22:12
 * 我的元梦购正在进行的
 */

public class MyDreamRecordingFragment extends BaseFragment implements MyDreamRecordView{

	MyDreamRecordingPM fragmentPM;

	@Override
	public int getlayoutId() {
		return R.layout.fragment_my_dream_record_ing;
	}

	@Override
	public Object initPM() {
		if(fragmentPM == null){
			fragmentPM = new MyDreamRecordingPM(this);
		}
		return fragmentPM;
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
		if (DreamApplication.getApp().eventBus() != null) {
			DreamApplication.getApp().eventBus().unregister(this);
		}
	}

	@Override
	public void onClick(View view, Object info) {

		MyDreamRecordingInfo inFo = (MyDreamRecordingInfo) info;

		Intent intent = new Intent(getActivity(), GoodInfoActivity.class);
		intent.putExtra(GoodInfoActivity.GOODID, String.valueOf(inFo.getShopid()));
		startActivity(new Intent(getActivity(), GoodInfoActivity.class));
	}

	@Override
	public void stopRefresh(View view) {
		((MaterialPullRefresh)view).refreshComplete();
	}

	@Override
	public void stopLoad(View view) {
		((XListView)view).stopLoadMore();
	}
}