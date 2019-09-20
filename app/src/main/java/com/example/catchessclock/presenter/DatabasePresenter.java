package com.example.catchessclock.presenter;

import android.util.Log;

import com.example.catchessclock.interfaces.DatabaseInterface;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.model.db.DBservise;
import com.example.catchessclock.model.db.models.TaskRealModel;

import java.util.ArrayList;
import java.util.List;

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
//        mDBservise.deleteAllData();
        Log.d(TAG, "attachView: ");
    }


    private void initRealm() {
        mDBservise = new DBservise();
        Log.d(TAG, "initRealm: ");
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

    public TimeControl getCurrentTime() {
        return  mDBservise.getTimeContrById(mDBservise.getCurrentId());
    }

    public int getCurrTimingId() {
        return mDBservise.getCurrentId();
    }

    public void getAdapter() {
        TimingsAdapter adapter = new TimingsAdapter(mDBservise.getAllTimings(),getCurrTimingId());
        getViewState().setTimingsList(adapter);
    }

    public void saveCurrentTiming(int pos) {
        mDBservise.saveCurrentId(pos);
    }


}
