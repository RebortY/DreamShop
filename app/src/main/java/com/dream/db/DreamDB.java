package com.dream.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dream.db.table.Login;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.impl.SQLiteHelper;

/**
 * Created by hanle on 15/8/2.
 */
public class DreamDB implements SQLiteHelper.OnUpdateListener{


    private final static String DBNAME = "dream.db";
    private final static int DBVERSION = 1;
    DataBase db = null;
    DataBaseConfig config = null;
    public DreamDB(Context ctx) {
        config = new DataBaseConfig(ctx, DBNAME, DBVERSION, this);
        db  = LiteOrm.newCascadeInstance(config);
    }
    public DataBase getdb(){
        return db;
    }

    @Override
    public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
