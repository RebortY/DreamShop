package com.dream.views.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.R;

/**
 * zhangyao
 * zhangyao@guoku.com
 * 15/9/22 18:35
 */
public class RelativeLayoutItem extends RelativeLayout {

    String editValue;

    private TextView textView;
    private EditText editText;

    public RelativeLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.relative_layou_item, this);

        init(context, attrs);
    }

    private void init(Context mContext, AttributeSet attrs){

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);

        TypedArray t = mContext.obtainStyledAttributes(attrs, R.styleable.LayoutItem);

        textView.setText(t.getString(R.styleable.LayoutItem_textValue));
        editText.setText(t.getString(R.styleable.LayoutItem_editValue));
        editText.setEnabled(t.getBoolean(R.styleable.LayoutItem_editEnabled, false));
    }

    public void setTextValue(String string){
        textView.setText(string);
    }

    public void setTextValue(int id){
        textView.setText(getResources().getString(id));
    }

    public String getTextValue(){
        return textView.getText().toString();
    }

    public void setEditTextValue(String string){
        editText.setText(string);
    }

    public void setEditTextValue(int id){
        editText.setText(getResources().getString(id));
    }

    public String getEditTextValue(){
        return editText.getText().toString();
    }

    public void setEditValue(String editValue) {
        this.editValue = editValue;
        setEditTextValue(editValue);
    }

    public void setEnabledEdit(boolean value){
        editText.setEnabled(value);
    }
}