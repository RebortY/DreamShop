package com.dream.views.gridview;

import android.content.Context;
import android.util.AttributeSet;

import com.paging.gridview.PagingGridView;

/**
 * @author yangll
 */
public class ScrollPagingGridView extends PagingGridView {
    public ScrollPagingGridView(Context context) {
        super(context);
    }

    public ScrollPagingGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollPagingGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 20, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
