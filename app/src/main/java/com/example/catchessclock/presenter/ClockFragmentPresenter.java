package com.example.catchessclock.presenter;

import com.example.catchessclock.view.ClockFragmentView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ClockFragmentPresenter extends MvpPresenter<ClockFragmentView> {

    @Override
    public void attachView(ClockFragmentView view) {
        super.attachView(view);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void startTimer() {
        getViewState().startTimer();
    }

    public void pauseTimer() {
        getViewState().pauseTimer();

    }



}
