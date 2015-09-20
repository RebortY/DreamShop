package com.dream.views.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.dream.R;

import java.text.MessageFormat;

/**
 * Created by yangll on 15/9/10.
 */
public class XProgressBar extends ProgressBar {

    Paint mPaint;
    /**
     * size 必须是 dimen 中定义的
     */
    int textSize = getResources().getDimensionPixelSize(R.dimen.pgTextSize);
    int textColor = R.color.progressbar_textcolor;
    int max;

    public XProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initText();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        String dt = formatText( max, getProgress());
        this.mPaint.getTextBounds(dt, 0, dt.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(dt, x, y, this.mPaint);
    }

    //初始化，画笔
    private void initText(){
        this.mPaint = new Paint();
        this.mPaint.setColor(getResources().getColor(textColor));
        this.mPaint.setTextSize(textSize);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        this.mPaint.setColor(getResources().getColor(textColor));
        invalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        this.mPaint.setTextSize(getResources().getDimension(textSize));
    }

    private String  formatText(int max , int progress){
        if(max == progress){
            return "已揭晓";
        }
        return MessageFormat.format("总 {0,number,#} / 剩 {1,number,#}" , max  , (max - progress) );
    }

    @Override
    public void setProgress(int progress) {
        super.setProgress(progress);
    }

    @Override
    public void setMax(int max) {
        super.setMax(max);
        this.max = max;
    }

    public void setMin(int min) {
    }
}
