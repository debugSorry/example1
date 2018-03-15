package com.example.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.dao.DaoMaster;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:29$
 * <p/>
 */
public class MyDataBaseOpenHelper extends DaoMaster.OpenHelper {
    public DataOpenHelper dataOpenHelper;

    public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, DataOpenHelper upgrade) {
        super(context, name, factory);
        if(upgrade != null){
            dataOpenHelper = upgrade;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        if(dataOpenHelper != null){
            dataOpenHelper.updata(db,oldVersion,newVersion);
        }
    }


    public interface DataOpenHelper{
        void updata(SQLiteDatabase db, int oldVersion, int newVersion);
    }
}
