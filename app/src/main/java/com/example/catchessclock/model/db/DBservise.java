package com.example.catchessclock.model.db;


import android.util.Log;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.model.db.migration.RealmMigration;
import com.example.catchessclock.model.db.models.CurrentTimingsModel;
import com.example.catchessclock.model.db.models.TaskRealModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by LasVegas on 25.04.2017.
 */

public class DBservise {

    public static final String TAG = DBservise.class.getName();


    public static RealmConfiguration mConfig = new RealmConfiguration.Builder()
            .schemaVersion(1)
            .migration(new RealmMigration() {
            })
            .build();


    public <T extends RealmObject> Observable<T> saveRx(T object, Class<T> clazz) {
        Realm realm = Realm.getDefaultInstance();

        long id;

        try {
            id = realm.where(clazz).max("id").intValue() + 1;
        } catch (Exception e) {
            id = 0L;
        }

        ((TaskRealModel) object).setId(id);

        return Observable.just(object)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(realm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            realm.commitTransaction();
                            realm.close();
                        })
                        .doOnNext(realm::copyToRealm)
                );
    }


    public <T extends RealmObject> Observable<List<T>> getAllRx(Class<T> clazz) {
//        Realm realm = Realm.getInstance(mConfig);
        Realm realm = Realm.getDefaultInstance();
        return Observable.just(clazz)
                .flatMap(t -> Observable.just(t)
                        .doOnSubscribe(realm::beginTransaction)
                        .doOnUnsubscribe(() -> {
                            realm.commitTransaction();
                            realm.close();
                        })
                        .map(type -> realm.where(type).findAll())
                );
    }


    public <T extends RealmObject> boolean save(TaskRealModel object, Class<T> clazz) {
//        Realm.removeDefaultConfiguration();
//        Realm realm = Realm.getInstance(mConfig);
        Realm realm = Realm.getDefaultInstance();
        long id;

        try {
            id = realm.where(clazz).max("id").intValue() + 1;
        } catch (Exception e) {
            id = 0L;
        }

        ((TaskRealModel) object).setId(id);

        realm.beginTransaction();
//        realm.create
        TaskRealModel obj = realm.createObject(TaskRealModel.class,object.getId());
        obj.setModel(object.getTitle(),
                object.getTimeLimit(),
                object.getIncrement(),
                object.getIncrementType());
//        obj.setId(id);
//        obj.setId(object.getId());
        realm.commitTransaction();
        realm.close();
        return true;
    }


    public List<TimeControl> getAllTimings() {
//        Realm.removeDefaultConfiguration();
//        Realm realm = Realm.getInstance(mConfig);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<TaskRealModel> results = realm.where(TaskRealModel.class).findAll();
        List<TimeControl> timings = new ArrayList<>();
        for (TaskRealModel model: results
             ) {
            TimeControl control = new TimeControl(model.getTimeLimit(),
                    model.getIncrement(),
                    model.getIncrementType(),
                    model.getTitle());
            control.setPrimaryKey( (int)(model.getId()) ) ;

            timings.add(control);
        }
        realm.commitTransaction();
        Log.d(TAG, "getAllTimings: ");
        realm.close();
        return timings;
    }


    public void saveCurrentId( int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CurrentTimingsModel model = new CurrentTimingsModel(id);
        realm.copyToRealmOrUpdate(model);
        realm.commitTransaction();
        realm.close();
    }

    public int getCurrentId() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CurrentTimingsModel model = realm.where(CurrentTimingsModel.class).
                equalTo("id",CurrentTimingsModel.ID).
                findFirst();
        realm.commitTransaction();
        int id = model.getSelectTimingsId();
        realm.close();
        if ( id < 0) {
            id = 0;
        }
        return id;
    }

    public TimeControl getCurrentTimeContr(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TaskRealModel model = realm.where(TaskRealModel.class).
                equalTo("id",id).findFirst();
        realm.commitTransaction();
        TimeControl control = model.getTimeControl();
        realm.close();
        return control;
    }

    public void deleteAllData() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

    public void addInitTimingsInDb() {
        TaskRealModel model = new TaskRealModel();
        model.setModel("name",1.f,1,0);
        save(model,TaskRealModel.class);

        model = new TaskRealModel();
        model.setModel("name",2.f,1,0);
        save(model,TaskRealModel.class);

        model = new TaskRealModel();
        model.setModel("name",3.f,1,0);
        save(model,TaskRealModel.class);

        model = new TaskRealModel();
        model.setModel("name",4.f,1,0);
        save(model,TaskRealModel.class);
    }

}
