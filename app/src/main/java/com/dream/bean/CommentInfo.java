package com.dream.bean;

/**
 * Created by yangll on 15/9/7.
 */
public class CommentInfo {

    /**
     * id : 2
     * sdhf_id : 2
     * sdhf_userid : 39
     * sdhf_username : null
     * sdhf_img : null
     * sdhf_time : 1436188898
     * sdhf_content : 我也想要
     */
    private int id;
    private int sdhf_id;
    private int sdhf_userid;
    private String sdhf_username;
    private String sdhf_img;
    private long sdhf_time;
    private String sdhf_content;

    public void setId(int id) {
        this.id = id;
    }

    public void setSdhf_id(int sdhf_id) {
        this.sdhf_id = sdhf_id;
    }

    public void setSdhf_userid(int sdhf_userid) {
        this.sdhf_userid = sdhf_userid;
    }

    public void setSdhf_username(String sdhf_username) {
        this.sdhf_username = sdhf_username;
    }

    public void setSdhf_img(String sdhf_img) {
        this.sdhf_img = sdhf_img;
    }

    public void setSdhf_time(long sdhf_time) {
        this.sdhf_time = sdhf_time;
    }

    public void setSdhf_content(String sdhf_content) {
        this.sdhf_content = sdhf_content;
    }

    public int getId() {
        return id;
    }

    public int getSdhf_id() {
        return sdhf_id;
    }

    public int getSdhf_userid() {
        return sdhf_userid;
    }

    public String getSdhf_username() {
        return sdhf_username;
    }

    public String getSdhf_img() {
        return sdhf_img;
    }

    public long getSdhf_time() {
        return sdhf_time;
    }

    public String getSdhf_content() {
        return sdhf_content;
    }
}
