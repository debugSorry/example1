package com.example.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.dao.DaoMaster;
import com.example.greendao.dao.DaoSession;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:19$
 * <p/>
 */
public class MyDatabaseLoader {
    private static DaoSession daoSession;
    public static void init(Context context,String databaseName,MyDataBaseOpenHelper.DataOpenHelper update){
            MyDataBaseOpenHelper openHelper = new MyDataBaseOpenHelper(context,databaseName,null,update);
            SQLiteDatabase database =  openHelper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(database);
            daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
