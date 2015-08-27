package com.dream.views.uitra;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import com.dream.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by yangll on 15/8/26.
 */
public class MaterialPullRefresh extends PtrFrameLayout {

    /**
     * type = 1 使用默认的 material_style
     */
    private int type = 1;
    private int mintime = 1000; //最小超时时间
    private boolean pincontent = false;//内容是否移动

    public MaterialPullRefresh(Context context) {
        super(context);
    }

    public MaterialPullRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialPullRefresh(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
        if(type == 1){
            // header
            final MaterialHeader header = new MaterialHeader(getContext());
            int[] colors = getResources().getIntArray(R.array.google_colors);
            header.setColorSchemeColors(colors);
            header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
            header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
            header.setPtrFrameLayout(this);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoRefresh(false);
                }
            }, 100);
            setHeaderView(header);
            addPtrUIHandler(header);
        }
    }

    public boolean isPincontent() {
        return pincontent;
    }

    public void setPincontent(boolean pincontent) {
        this.pincontent = pincontent;
        setPinContent(pincontent);
    }

    public int getMintime() {
        return mintime;
    }

    public void setMintime(int mintime) {
        this.mintime = mintime;
        setLoadingMinTime(mintime);
    }

}
