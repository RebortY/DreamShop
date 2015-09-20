package com.dream.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/8/20.
 */
@Table(value = "login_users")
public class AuthUser {

    public static final String COL_LASTTIME = "login_time";
    /**
     * uid : 1077
     * username :
     * time : 1438260337
     * login_time : 1438521321
     * email : null
     * img : photo/member.jpg
     * money : 0
     * qianming : null
     * mobile : 13401165595
     */
    @NotNull
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    private int uid;
    @NotNull
    @Default("dream_user")
    private String username;
    private int time;
    @Column(COL_LASTTIME)
    private int login_time;
    private String email;
    private String img;
    private int money;
    private String qianming;
    private String mobile;
    private String password;
    private String yungoudj;//用户头衔
    @Ignore
    private boolean isLogin;


    public String getYungoudj() {
        return yungoudj;
    }

    public void setYungoudj(String yungoudj) {
        this.yungoudj = yungoudj;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLogin_time(int login_time) {
        this.login_time = login_time;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setQianming(String qianming) {
        this.qianming = qianming;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public int getTime() {
        return time;
    }

    public int getLogin_time() {
        return login_time;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public int getMoney() {
        return money;
    }

    public String getQianming() {
        return qianming;
    }

    public String getMobile() {
        return mobile;
    }
}
