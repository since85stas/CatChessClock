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

        Log.d(TAG, "onFirstViewAttach: ");
    }

    @Override
    public void attachView(MainActivityView view) {
        super.attachView(view);
        mDBservise = new DBservise();
        getViewState().initMainActivity(getTimeControl(),getTimeControl());
        Log.d(TAG, "attachView: init");
    }

    private TimeControl getTimeControl() {

        return mDBservise.getTimeContrById(mDBservise.getCurrentId());
    }


}
