package com.dream.bean.shopcart;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/9/15.
 */
@Table(value = "shopcart")
public class ShopBean {

    @NotNull
    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    private int autioId;
    //用户id
    public int uid;
    //商品sid
    public String sid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getAutioId() {
        return autioId;
    }

    public void setAutioId(int autioId) {
        this.autioId = autioId;
    }
}
