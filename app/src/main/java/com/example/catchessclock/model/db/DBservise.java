package com.example.catchessclock.model.db;


import android.util.Log;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.model.db.migration.RealmMigration;
import com.example.catchessclock.model.db.models.CurrentTimingsModel;
import com.example.catchessclock.model.db.models.TaskRealModel;
import com.example.catchessclock.model.db.models.TimeStageModel;

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


    public static RealmConfiguration mConfig = new RealmConfiguration.Builder().
            initialData(realm -> {
                TaskRealModel model = new TaskRealModel();

                long id = 0;
                model.setTitle("init");
                model.setId(id);
                TimeStageModel stageModel = new TimeStageModel(id);
                stageModel.setModel(10, 3, -1, 0);
                realm.copyToRealm(model);
                realm.copyToRealm(stageModel);

                model = new TaskRealModel();
                id = 1;
                model.setTitle("init1");
                model.setId(id);
                TimeStageModel stageModel2 = new TimeStageModel(id);
                stageModel2.setModel(20, 3, -1, 0);
                realm.copyToRealm(model);
                realm.copyToRealm(stageModel2);

                model = new TaskRealModel();
                id = 2;
                model.setTitle("init2");
                model.setId(id);
                TimeStageModel stageModel3 = new TimeStageModel(id);
                stageModel3.setModel(20, 3, -1, 0);
                realm.copyToRealm(model);
                realm.copyToRealm(stageModel3);
                realm.copyToRealm(stageModel3);

            })
            .schemaVersion(2)
            .migration(new RealmMigration() {
            }).deleteRealmIfMigrationNeeded()
            .build();


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
        TaskRealModel obj = realm.createObject(TaskRealModel.class, object.getId());
        obj.setId(id);
        realm.commitTransaction();
        realm.close();
        return true;
    }


    public List<TimeControl> getAllTimings() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<TaskRealModel> results = realm.where(TaskRealModel.class).findAll();
        List<TimeControl> timings = new ArrayList<>();
        for (TaskRealModel model : results
        ) {
            RealmResults<TimeStageModel> stageModels = realm.where(TimeStageModel.class).
                    equalTo("modelId", model.getId()).findAll();
            TimeControl control = model.getTimeControl();
            for (int i = 0; i < stageModels.size(); i++) {
                control.addStage(stageModels.get(i).getTimeLimit(),
                        stageModels.get(i).getIncrement(),
                        stageModels.get(i).getIncrementType(),
                        stageModels.get(i).getTurnLimit());
            }
            timings.add(control);
        }
        realm.commitTransaction();
        Log.d(TAG, "getAllTimings: ");
        realm.close();
        return timings;
    }


    public void saveCurrentId(int id) {
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
                equalTo("id", CurrentTimingsModel.ID).
                findFirst();
        realm.commitTransaction();
        int id;
        try {
            id = model.getSelectTimingsId();
        } catch (NullPointerException e) {
            id = 0;
        }
//        realm.close();
        if (id < 0) {
            id = 0;
        }
        realm.close();
        return id;
    }

    public TimeControl getTimeContrById(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        TaskRealModel model = realm.where(TaskRealModel.class).
                equalTo("id", id).findFirst();

        realm.commitTransaction();

        realm.beginTransaction();
        RealmResults<TimeStageModel> stageModels = realm.where(TimeStageModel.class).
                equalTo("modelId", model.getId()).findAll();
        realm.commitTransaction();
        TimeControl control = model.getTimeControl();
        for (int i = 0; i < stageModels.size(); i++) {
            control.addStage(stageModels.get(i).getTimeLimit(),
                    stageModels.get(i).getIncrement(),
                    stageModels.get(i).getIncrementType(),
                    stageModels.get(i).getTurnLimit());
        }
        realm.close();
        return control;
    }

    public TimeControl getCurrentTimeControl() {
        Realm realm = Realm.getDefaultInstance();
        int id = getCurrentId();

        realm.beginTransaction();
        TaskRealModel model = realm.where(TaskRealModel.class).
                equalTo("id", id).findFirst();
        realm.commitTransaction();

        realm.beginTransaction();
        RealmResults<TimeStageModel> stageModels = realm.where(TimeStageModel.class).
                equalTo("modelId", model.getId()).findAll();
        realm.commitTransaction();
        TimeControl control = model.getTimeControl();
        for (int i = 0; i < stageModels.size(); i++) {
            control.addStage(stageModels.get(i).getTimeLimit(),
                    stageModels.get(i).getIncrement(),
                    stageModels.get(i).getIncrementType(),
                    stageModels.get(i).getTurnLimit());
        }
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


}
