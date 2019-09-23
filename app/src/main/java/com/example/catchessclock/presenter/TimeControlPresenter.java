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
        getAdapter();
    }

    public void saveButtonClicked() {
        mDBservise.save(mTimecontrol);
        getViewState().saveButtonClicked();
    }

    public void changeIncreamnet(int minutes,int sec,int incrType) {
        mTimecontrol.getTimeStage().increment = minutes*60 + sec;
        mTimecontrol.getTimeStage().incrementType = incrType;
        getViewState().setActivityState(mTimecontrol);
    }

    public void addTimeStage(int hours, int min , int sec, int turnLimit) {
        int time = hours*3600 + min*60 + sec;
        mTimecontrol.addTimeStageByTimelimitAndTurnLimit(time,turnLimit);
        getAdapter();
    }

    public void getAdapter() {
        TimingsSelectAdapter adapter = new TimingsSelectAdapter(mTimecontrol.mStageList);
        getViewState().setTimingsSelectList(adapter);
    }

    public void redactionTimeStage(int hours, int minutes, int sec, int turnLimit, int stagePos) {
        int time = hours*3600 + minutes*60 + sec;
        mTimecontrol.mStageList.get(stagePos).timeLimit = time;
        mTimecontrol.mStageList.get(stagePos).turnLimit = turnLimit;
        getAdapter();
    }

//    public void addStageClicked() {
//
//        getViewState().addTimeStage();
//    }

//    private void initViewState(TimeControl control) {
//        mActivityView.m
//    }


}
