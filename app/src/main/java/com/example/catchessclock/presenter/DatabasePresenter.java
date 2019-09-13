package com.example.catchessclock.presenter;

import android.content.Context;
import android.util.Log;

import com.example.catchessclock.interfaces.DatabaseInterface;
import com.example.catchessclock.model.db.DBservise;
import com.example.catchessclock.model.db.models.TaskRealModel;
import com.example.catchessclock.view.SettingsActivityView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class DatabasePresenter extends MvpPresenter<DatabaseInterface> {

    public static final String TAG = DatabasePresenter.class.getName();

    DBservise mDBservise;

    public DatabasePresenter() {
//        mContex = contex;
    }

    @Override
    public void attachView(DatabaseInterface view) {
        super.attachView(view);
        initRealm();
        getAdapter();
//        mDBservise.saveCurrentId(3);
//        int i = mDBservise.getCurrentId();
//        mDBservise.addInitTimingsInDb();
        Log.d(TAG, "attachView: ");
//        saveToDb();
//        getAllModels();
//        getAllModels();
    }


    private void initRealm() {
        mDBservise = new DBservise();
        Log.d(TAG, "initRealm: ");
    }

    private void saveToDb() {
        TaskRealModel model = new TaskRealModel();
        model.setModel("1",3,1,0);
        mDBservise.save(model,TaskRealModel.class);
        Log.d(TAG, "saveRealm: ");
    }

    private void saveToDbRx() {
        TaskRealModel model = new TaskRealModel();
        model.setModel("1",3,1,0);
        mDBservise.saveRx(model,TaskRealModel.class);
        Log.d(TAG, "saveRealm: ");
    }


    private void getAllModelsRx() {
        List<TaskRealModel> list = new ArrayList<>();
        mDBservise.getAllRx(TaskRealModel.class)
                .subscribe( taskRealModels -> {
                    for (TaskRealModel model: taskRealModels) {
                        list.add(model);
                    }
                    Log.d(TAG, "getAllModels: ");
                } );
    }

//    private void getAllModels() {
//        mDBservise.getAllTimings();
//    }

    public void getAdapter() {
        TimingsAdapter adapter = new TimingsAdapter(mDBservise.getAllTimings());
        getViewState().setTimingsList(adapter);
    }


}
