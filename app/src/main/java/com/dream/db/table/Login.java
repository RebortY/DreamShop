package com.dream.db.table;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by yangll on 15/8/2.
 * 此类可以 数据建模，也是 数据库建模对象
 */
@Table(value = "login")
public class Login {

    @NotNull
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    public String id; //登录用户 唯一ID
    public String name;//用户名

    @Ignore
    public String dex;
}
