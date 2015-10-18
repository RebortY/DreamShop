package com.dream.views.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.R;
import com.github.snowdream.android.util.Log;


/**
 * zhangyaos
 * zhangyao@guoku.com
 * 15/8/22 15:32
 */
public class LayoutItem extends RelativeLayout {

    public ImageView leftImg, rightImg, centreImg;
    public TextView leftText, rightText;


    public LayoutItem(Context context) {
        super(context);
    }

    public LayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    public LayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_item, this);

        leftImg = (ImageView)findViewById(R.id.imageView);
        rightImg = (ImageView)findViewById(R.id.imageView2);
        leftText = (TextView)findViewById(R.id.textview);
        rightText = (TextView)findViewById(R.id.textview2);
        centreImg = (ImageView)findViewById(R.id.imageView3);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.LayoutItem);

        String textValue = t.getString(R.styleable.LayoutItem_textValue);
        String textValueRight = t.getString(R.styleable.LayoutItem_textValueRight);
        float textSize = t.getDimensionPixelSize(R.styleable.LayoutItem_textSize, 14);
        Log.d("textSize =  "+ textSize);
        int textColor = t.getColor(R.styleable.LayoutItem_textColor, getResources().getColor(R.color.listtab_off));
        int leftImgs  = t.getResourceId(R.styleable.LayoutItem_leftImgBackground, R.drawable.tab_publish_activ);
        int rightImgs  = t.getResourceId(R.styleable.LayoutItem_rightImgBackground, R.drawable.arror_right);
        int centreImgs  = t.getResourceId(R.styleable.LayoutItem_centreImgBackground, R.drawable.tab_account_activ);
        boolean leftImgVisable = t.getBoolean(R.styleable.LayoutItem_leftImgVisable, false);
        boolean rightImgVisable = t.getBoolean(R.styleable.LayoutItem_rightImgVisable , false );

        leftText.setText(textValue);

        leftText.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        leftText.setTextColor(textColor);
        leftImg.setImageResource(leftImgs);
        rightImg.setImageResource(rightImgs);
        rightText.setText(textValueRight);
        centreImg.setImageResource(centreImgs);
        leftImg.setVisibility(leftImgVisable ? View.VISIBLE : View.GONE);
        rightImg.setVisibility(rightImgVisable ? View.VISIBLE : View.GONE);
    }

    public void setText(int strId){
        leftText.setText(getResources().getString(strId));
    }
    public void setText(String str){
        leftText.setText(str);
    }
}
