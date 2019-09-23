package com.example.catchessclock.presenter;

import android.util.Log;

import com.example.catchessclock.entities.ChessClock;
import com.example.catchessclock.view.ClockFragmentView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ClockFragmentPresenter extends MvpPresenter<ClockFragmentView>  {

    @Override
    public void attachView(ClockFragmentView view) {
        super.attachView(view);
        getViewState().setDefaultTextViewBackground();
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().setDefaultTextViewBackground();
        super.onFirstViewAttach();
    }

    public void startTimer() {
        getViewState().startTimer();
        getViewState().changeTextViewBackground();
    }

    public void pauseTimer() {
        getViewState().pauseTimer();
        getViewState().setDefaultTextViewBackground();
    }

    public void nextTurn() {
        getViewState().nextTurn();
        getViewState().changeTextViewBackground();
    }

    public void setFragmentLooseState() {
        getViewState().setLooseBackgroundState();;
    }
}
