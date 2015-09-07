package com.dream.bean;

/**
 * Created by yangll on 15/9/7.
 */
public class Category {

    /**
     * catdir : diannao
     * cateid : 13
     * model : 1
     * order : 5
     * name : 电脑办公
     * parentid : 0
     * channel : 0
     * url :
     * info :
     */
    private String catdir;
    private int cateid;
    private int model;
    private int order;
    private String name;
    private int parentid;
    private int channel;
    private String url;
    private String info;

    public void setCatdir(String catdir) {
        this.catdir = catdir;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCatdir() {
        return catdir;
    }

    public int getCateid() {
        return cateid;
    }

    public int getModel() {
        return model;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public int getParentid() {
        return parentid;
    }

    public int getChannel() {
        return channel;
    }

    public String getUrl() {
        return url;
    }

    public String getInfo() {
        return info;
    }
}
