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
    public long autioId;
    //用户id
    public int uid;
    //商品sid
    public String id;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAutioId() {
        return autioId;
    }

    public void setAutioId(long autioId) {
        this.autioId = autioId;
    }
}
