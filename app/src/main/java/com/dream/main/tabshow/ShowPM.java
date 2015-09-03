package com.dream.main.tabshow;

import com.alibaba.fastjson.JSON;
import com.dream.bean.GoodForm;
import com.dream.main.DreamApplication;
import com.dream.main.tabshow.items.ShowItemPM;
import com.dream.net.NetResponse;
import com.dream.net.business.ProtocolUrl;
import com.dream.util.ToastUtil;
import com.dream.views.AbstractPM;
import com.dream.views.uitra.MaterialPullRefresh;
import com.dream.views.uitra.MaterialPullRefreshEvent;

import org.json.JSONException;
import org.json.JSONObject;
import org.robobinding.annotation.ItemPresentationModel;
import org.robobinding.annotation.PresentationModel;
import org.robobinding.widget.adapterview.ItemClickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import control.annotation.Subcriber;
import eb.eventbus.ThreadMode;

/**
 * Created by yangll on 15/8/22.
 */
@PresentationModel
public class ShowPM extends AbstractPM {

    private final String EVENTTAG = "SHOW_TAG";

    private ShowView view;
    private List<GoodForm> data = new ArrayList<GoodForm>();

    private int page = 1;
    private int size = 10;
    private int total = 0;

    private MaterialPullRefreshEvent tempEvent;

    public ShowPM(ShowView view) {
        this.view = view;
        DreamApplication.getApp().eventBus().register(this);
        getDataPage();
    }

    private void getDataPage() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        DreamApplication.getApp().getDreamNet().netJsonPost(EVENTTAG, ProtocolUrl.POSTLIST, params);
    }

    @ItemPresentationModel(value = ShowItemPM.class , factoryMethod = "creatShowItemPM")
    public List<GoodForm> getData() {
        return data;
    }

    public ShowItemPM creatShowItemPM(){
        return new ShowItemPM(view);
    }

    /**
     * 点击每一项
     * @param event
     */
    public void clickItem(ItemClickEvent event){
        ToastUtil.show("点我，我该跳哪里呢");
    }


    @Subcriber(tag = EVENTTAG, threadMode = ThreadMode.MainThread)
    public void handlResp(NetResponse response) {
        if (response.getRespType() == NetResponse.SUCCESS) {
            try {
                JSONObject object = ((JSONObject) response.getResp()).getJSONObject("data");
                total = object.getInt("total");
                String jstr = object.getJSONArray("list").toString();
                List<GoodForm> forms = JSON.parseArray(jstr, GoodForm.class);
                setData(forms);
            } catch (JSONException e) {
                ToastUtil.show("JSON 异常");
            }
        }
        if (tempEvent != null)
            view.stopRefresh(tempEvent.getView());
    }

    public void setData(List<GoodForm> data) {
        if (size >= total) {
            //TODO 关闭加载更多 return
            return;
        }
        if (page == 1) this.data.clear();
        this.data.addAll(data);
        getPresentationModelChangeSupport().firePropertyChange("data");
    }


    /**
     * 下拉刷新
     */
    public void refresh(MaterialPullRefreshEvent event) {
        tempEvent = event;
        page = 1;
        getDataPage();
    }

}
