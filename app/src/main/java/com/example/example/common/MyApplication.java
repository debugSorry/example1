package com.example.example.common;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.MigrationHelper;
import com.example.greendao.MyDataBaseOpenHelper;
import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.DaoMaster;
import com.example.greendao.dao.PersonDao;

import org.greenrobot.greendao.database.Database;

import cn.feng.skin.manager.loader.SkinManager;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/23$ 9:10$
 * <p/>
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
//        Realm.init(this);
//        RealmConfiguration configuration = new RealmConfiguration.Builder().name("test.realm")
//                .schemaVersion(1)
//                .migration(new CustomMigration())
//                .build();
//        Realm.setDefaultConfiguration(configuration);
        MyDatabaseLoader.init(this, "greendao_db", new MyDataBaseOpenHelper.DataOpenHelper() {
            @Override
            public void updata(SQLiteDatabase db, int oldVersion, int newVersion) {
                if(oldVersion ==1 && newVersion == 2){
                    MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                        @Override
                        public void onCreateAllTables(Database db, boolean ifNotExists) {
                            DaoMaster.createAllTables(db, ifNotExists);
                        }

                        @Override
                        public void onDropAllTables(Database db, boolean ifExists) {
                            DaoMaster.dropAllTables(db, ifExists);
                        }
                    }, PersonDao.class);
                }
            }
        });
    }
}
