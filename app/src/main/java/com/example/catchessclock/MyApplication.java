package com.example.catchessclock;

import android.app.Application;

import com.example.catchessclock.model.db.DBservise;

import io.realm.Realm;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        Realm.deleteRealm(DBservise.mConfig);
        Realm.setDefaultConfiguration(DBservise.mConfig);
    }
}
