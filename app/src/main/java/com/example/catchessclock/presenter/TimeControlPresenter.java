package com.example.catchessclock.presenter;


import android.util.Log;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.model.db.DBservise;
import com.example.catchessclock.view.TimeControlActivity;
import com.example.catchessclock.view.TimeControlActivityView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class TimeControlPresenter extends MvpPresenter<TimeControlActivityView> {

    public static final String TAG = TimeControlActivity.class.getName();

    DBservise mDBservise;

    TimeControl initTimeControl;

    @Override
    public void attachView(TimeControlActivityView view) {

        super.attachView(view);
        mDBservise = new DBservise();
        Log.d(TAG, "attachView: create");
        initTimeControl = mDBservise.getCurrentTimeControl();
//        initViewState(initTimeControl);
        getViewState().setActivityState(initTimeControl);
    }

    public void saveButtonClicked() {
        getViewState().saveButtonClicked();
    }

//    private void initViewState(TimeControl control) {
//        mActivityView.m
//    }


}
