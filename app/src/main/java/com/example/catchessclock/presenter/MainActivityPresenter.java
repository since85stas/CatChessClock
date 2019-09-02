package com.example.catchessclock.presenter;

import android.util.Log;


import com.example.catchessclock.view.MainActivityView;

import moxy.MvpPresenter;


public class MainActivityPresenter  extends MvpPresenter<MainActivityView> {

    public static final String TAG = MainActivityPresenter.class.getName();

    public MainActivityPresenter() {
        getViewState().initPlayerTime(10);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
//        getViewState().initPlayerTime(10);
        Log.d(TAG, "onFirstViewAttach: ");
    }

    @Override
    public void attachView(MainActivityView view) {
        super.attachView(view);
//        getViewState().initPlayerTime(10);
        Log.d(TAG, "attachView: init");
    }
}
