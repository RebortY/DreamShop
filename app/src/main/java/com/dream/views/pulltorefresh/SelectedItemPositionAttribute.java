package com.dream.views.pulltorefresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.dream.views.pulltorefresh.event.PullAdapterViewAddOn;
import com.slib.pulltoviews.PullToRefreshAdapterViewBase;

import org.robobinding.property.ValueModel;
import org.robobinding.viewattribute.property.TwoWayPropertyViewAttribute;
import org.robobinding.widgetaddon.adapterview.AdapterViewAddOn;

/**
 * 
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public class SelectedItemPositionAttribute implements TwoWayPropertyViewAttribute<PullToRefreshAdapterViewBase, PullAdapterViewAddOn, Integer> {

	@Override
	public void updateView(PullToRefreshAdapterViewBase pullToRefreshAdapterViewBase, Integer position, PullAdapterViewAddOn adapterViewAddOn) {
		((AbsListView)pullToRefreshAdapterViewBase.getRefreshableView()).setSelection(position);
	}

	@Override
	public void observeChangesOnTheView(PullAdapterViewAddOn adapterViewAddOn, final ValueModel<Integer> valueModel, PullToRefreshAdapterViewBase pullToRefreshAdapterViewBase) {
		adapterViewAddOn.addOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				valueModel.setValue(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				valueModel.setValue(AdapterView.INVALID_POSITION);
			}
		});
	}
}
