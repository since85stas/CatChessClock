package com.example.catchessclock.presenter;

import android.util.Log;
import android.view.View;


import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.view.MainActivityView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainActivityPresenter  extends MvpPresenter<MainActivityView> {

    public static final String TAG = MainActivityPresenter.class.getName();

    public MainActivityPresenter() {
//        getViewState().initPlayerTime(10);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().initMainActivity(getTimeControl(),getTimeControl());
        Log.d(TAG, "onFirstViewAttach: ");
    }

    @Override
    public void attachView(MainActivityView view) {
        super.attachView(view);
        Log.d(TAG, "attachView: init");
    }

    private TimeControl getTimeControl() {

        return new TimeControl(5,1,TimeControl.TYPE_FISHER, "qq");
    }


}
