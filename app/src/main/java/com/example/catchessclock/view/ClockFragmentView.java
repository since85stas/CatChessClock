package com.example.catchessclock.view;

import moxy.MvpView;

public interface ClockFragmentView extends MvpView {

    public void clockChange();

    public void startTimer();

    public void pauseTimer();

}
