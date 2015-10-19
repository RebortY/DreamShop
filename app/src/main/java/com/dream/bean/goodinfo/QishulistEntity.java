package com.dream.bean.goodinfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangll on 15/9/13.
 */
public class QishulistEntity implements Parcelable {

    /**
     * id : 569
     * gonumber_total : 16
     * q_uid : 0
     * q_user : null
     * q_end_time : null
     * q_user_code : null
     * qiname : 第56期
     */

    private int id;
    private int gonumber_total;
    private int q_uid;
    private User q_user;
    private String q_end_time;
    private String q_user_code;
    private String qiname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGonumber_total() {
        return gonumber_total;
    }

    public void setGonumber_total(int gonumber_total) {
        this.gonumber_total = gonumber_total;
    }

    public int getQ_uid() {
        return q_uid;
    }

    public void setQ_uid(int q_uid) {
        this.q_uid = q_uid;
    }

    public User getQ_user() {
        return q_user;
    }

    public void setQ_user(User q_user) {
        this.q_user = q_user;
    }

    public String getQ_end_time() {
        return q_end_time;
    }

    public void setQ_end_time(String q_end_time) {
        this.q_end_time = q_end_time;
    }

    public String getQ_user_code() {
        return q_user_code;
    }

    public void setQ_user_code(String q_user_code) {
        this.q_user_code = q_user_code;
    }

    public String getQiname() {
        return qiname;
    }

    public void setQiname(String qiname) {
        this.qiname = qiname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.gonumber_total);
        dest.writeInt(this.q_uid);
        dest.writeParcelable(this.q_user, flags);
        dest.writeString(this.q_end_time);
        dest.writeString(this.q_user_code);
        dest.writeString(this.qiname);
    }

    public QishulistEntity() {
    }

    protected QishulistEntity(Parcel in) {
        this.id = in.readInt();
        this.gonumber_total = in.readInt();
        this.q_uid = in.readInt();
        this.q_user = in.readParcelable(User.class.getClassLoader());
        this.q_end_time = in.readString();
        this.q_user_code = in.readString();
        this.qiname = in.readString();
    }

    public static final Creator<QishulistEntity> CREATOR = new Creator<QishulistEntity>() {
        public QishulistEntity createFromParcel(Parcel source) {
            return new QishulistEntity(source);
        }

        public QishulistEntity[] newArray(int size) {
            return new QishulistEntity[size];
        }
    };
}
