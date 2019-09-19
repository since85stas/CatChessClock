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

    TimeControl mTimecontrol;

    @Override
    public void attachView(TimeControlActivityView view) {

        super.attachView(view);
        mDBservise = new DBservise();
        Log.d(TAG, "attachView: create");
        mTimecontrol = mDBservise.getCurrentTimeControl();
//        initViewState(mTimecontrol);
        getViewState().setActivityState(mTimecontrol);
    }

    public void saveButtonClicked() {
        getViewState().saveButtonClicked();
    }

    public void changeIncreamnet(int minutes,int sec,int incrType) {
        mTimecontrol.increment = minutes*60 + sec;
        mTimecontrol.incrementType = incrType;
        getViewState().setActivityState(mTimecontrol);
    }

//    private void initViewState(TimeControl control) {
//        mActivityView.m
//    }


}
