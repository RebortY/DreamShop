package com.dream.db.table;

import com.dream.bean.AuthUser;
import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/8/2.
 * 登录信息
 */
public class Login {

    private String token;
    private AuthUser user;
    private int fufen_yuan;

    public int getFufen_yuan() {
        return fufen_yuan;
    }

    public void setFufen_yuan(int fufen_yuan) {
        this.fufen_yuan = fufen_yuan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }
}
