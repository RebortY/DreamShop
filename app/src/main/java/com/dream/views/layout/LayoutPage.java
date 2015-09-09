package com.dream.views.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.dream.R;
import com.dream.util.ToastUtil;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/8 22:53
 * 通用标签分页按钮
 */
public class LayoutPage extends LinearLayout implements CompoundButton.OnCheckedChangeListener{

    RadioButton btLeft, btRight;

    View viewLeft, viewRight;

    public LayoutPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_layoutpage, this);

        init();
    }

    private void init(){

        btLeft = (RadioButton) findViewById(R.id.bt_left);
        btRight = (RadioButton) findViewById(R.id.bt_right);

        viewLeft = findViewById(R.id.img_left);
        viewRight = findViewById(R.id.img_right);

        btLeft.setOnCheckedChangeListener(this);
        btRight.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView == btLeft){

            ToastUtil.show("左边");

        }
        if(buttonView == btRight){

            ToastUtil.show("右边");
        }
    }
}
