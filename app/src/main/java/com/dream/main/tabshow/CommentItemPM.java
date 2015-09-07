package com.dream.main.tabshow;

import com.dream.bean.CommentInfo;
import com.dream.util.DreamUtils;

import org.robobinding.itempresentationmodel.ItemContext;
import org.robobinding.itempresentationmodel.ItemPresentationModel;


/**
 * Created by yangll on 15/9/7.
 */
public class CommentItemPM implements ItemPresentationModel<CommentInfo> {

    CommentInfo info = null;
    private boolean circle = true;
    @Override
    public void updateData(CommentInfo commentInfo, ItemContext itemContext) {
            info = commentInfo;
    }

    public String getUrl() {
        return info.getSdhf_img();
    }

    public boolean isCircle() {
        return circle;
    }

    public String getName() {
        return info.getSdhf_username();
    }

    public String getTime() {
        return DreamUtils.formatSecTime(info.getSdhf_time(),"yyy-MM-dd");
    }

    public String getContent() {
        return info.getSdhf_content();
    }
}
