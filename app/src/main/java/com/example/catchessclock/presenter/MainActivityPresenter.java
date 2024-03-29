package com.example.catchessclock.presenter;

import android.util.Log;


import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.model.db.DBservise;
import com.example.catchessclock.view.MainActivityView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainActivityPresenter  extends MvpPresenter<MainActivityView> {

    public static final String TAG = MainActivityPresenter.class.getName();

    DBservise mDBservise;



    public MainActivityPresenter() {
//        getViewState().initPlayerTime(10);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mDBservise = new DBservise();
        getViewState().initMainActivity(getTimeControl(),getTimeControl());
        Log.d(TAG, "onFirstViewAttach: ");
    }

    @Override
    public void attachView(MainActivityView view) {
        super.attachView(view);

        Log.d(TAG, "attachView: init");
    }

    private TimeControl getTimeControl() {

//        return mDBservise.getTimeContrById(mDBservise.getCurrentId());
        TimeControl controlDb = mDBservise.getCurrentTimeControl();
//        TimeControl control = new TimeControl("11");
//        control.addStage(10,1,-1);
        return controlDb;
    }

    public void timeIsOut(String fragmTag){
        getViewState().pauseButtonClick();
        getViewState().timeIsOut(fragmTag);
    }
}
