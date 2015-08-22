package com.dream.views.imageview;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;

/**
 * Created by yangll on 15/8/22.
 */
public class DreamImageView extends com.facebook.drawee.view.SimpleDraweeView {

    private String url; //url 地址，支持本地 URI
    private boolean circle; //圆圈

    public DreamImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public DreamImageView(Context context) {
        super(context);
    }

    public DreamImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DreamImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setUrl(String url) {
        this.url = url;
        Uri uri = Uri.parse(url);
        setImageURI(uri);
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
        if(getHierarchy().getRoundingParams() != null)
            getHierarchy().getRoundingParams().setRoundAsCircle(circle);
        else{
            GenericDraweeHierarchy hierarchy =  getHierarchy();
            RoundingParams params = new RoundingParams();
            params.setRoundAsCircle(circle);
            hierarchy.setRoundingParams(params);
        }
    }
}
