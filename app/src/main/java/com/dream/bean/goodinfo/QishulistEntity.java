package com.dream.bean.goodinfo;

/**
 * Created by yangll on 15/9/13.
 */
public class QishulistEntity {
    /**
     * id : 544
     * q_end_time : null
     * qiname : 第51期
     */
    private int id;
    private String q_end_time;
    private String qiname;

    public void setId(int id) {
        this.id = id;
    }

    public void setQ_end_time(String q_end_time) {
        this.q_end_time = q_end_time;
    }

    public void setQiname(String qiname) {
        this.qiname = qiname;
    }

    public int getId() {
        return id;
    }

    public String getQ_end_time() {
        return q_end_time;
    }

    public String getQiname() {
        return qiname;
    }
}
