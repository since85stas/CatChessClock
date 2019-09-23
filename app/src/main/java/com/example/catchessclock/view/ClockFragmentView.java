package com.example.catchessclock.view;

import com.example.catchessclock.model.TimeControl;

import moxy.MvpView;

public interface ClockFragmentView extends MvpView {

    public void clockChange();

    public void startTimer();

    public void pauseTimer();

    public void setClockInitTimer(TimeControl timeControl);

    void clockIsPressed();

    void nextTurn();

    void changeTextViewBackground();

    void setDefaultTextViewBackground();

    void setLooseBackgroundState();
}
