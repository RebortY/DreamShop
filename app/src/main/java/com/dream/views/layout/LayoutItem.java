package com.dream.views.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.R;


/**
 * zhangyaos
 * zhangyao@guoku.com
 * 15/8/22 15:32
 */
public class LayoutItem extends RelativeLayout {

    public ImageView leftImg, rightImg, centreImg;
    public TextView leftText, rightText;


    public LayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_item, this);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){

        leftImg = (ImageView)findViewById(R.id.imageView);
        rightImg = (ImageView)findViewById(R.id.imageView2);
        leftText = (TextView)findViewById(R.id.textview);
        rightText = (TextView)findViewById(R.id.textview2);
        centreImg = (ImageView)findViewById(R.id.imageView3);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.LayoutItem);

        String textValue = t.getString(R.styleable.LayoutItem_textValue);
        String textValueRight = t.getString(R.styleable.LayoutItem_textValueRight);
        float textSize = t.getDimension(R.styleable.LayoutItem_textSize, 18);
        int textColor = t.getColor(R.styleable.LayoutItem_textColor, 0xff000000);
        int leftImgs  = t.getResourceId(R.styleable.LayoutItem_leftImgBackground, R.drawable.tab_publish_activ);
        int rightImgs  = t.getResourceId(R.styleable.LayoutItem_rightImgBackground, R.drawable.abc_ic_go_search_api_mtrl_alpha);
        int centreImgs  = t.getResourceId(R.styleable.LayoutItem_centreImgBackground, R.drawable.tab_account_activ);

        leftText.setText(textValue);
        leftText.setTextSize(textSize);
        leftText.setTextColor(textColor);
        leftImg.setImageResource(leftImgs);
        rightImg.setImageResource(rightImgs);
        rightText.setText(textValueRight);
        centreImg.setImageResource(centreImgs);
    }


}
