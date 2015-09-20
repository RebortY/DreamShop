package com.dream.bean.goodinfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangll on 15/9/15.
 */
public class User implements Parcelable {

    /**
     * uid : 90
     * username :
     * email :
     * img : http://m.1yuanmeng.com/statics/uploads/photo/member.jpg
     * mobile : 15047833449
     */
    private String uid;
    private String username;
    private String email;
    private String img;
    private String mobile;



    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.img);
        dest.writeString(this.mobile);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.uid = in.readString();
        this.username = in.readString();
        this.email = in.readString();
        this.img = in.readString();
        this.mobile = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
