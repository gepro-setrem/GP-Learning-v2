package com.gplearning.gplearning.DAO;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gplearning.gplearning.Models.DaoMaster;
import com.gplearning.gplearning.Models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,ENCRYPTED ? "testGPL-db-encrypted" : "testGPL-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public  static DaoSession getDaoSessionApp(Context context){
        DaoMaster.DevOpenHelper  masterHelper = new DaoMaster.DevOpenHelper(context, "testGPL-db", null);
        SQLiteDatabase db = masterHelper.getWritableDatabase();  //get the created database db file
        DaoMaster master = new DaoMaster(db);//create masterDao
        //DaoSession masterSession=master.newSession();
        return  master.newSession();
    }
}
