package com.example.catchessclock.view;


import com.example.catchessclock.model.TimeControl;

import moxy.MvpView;

public interface MainActivityView extends MvpView {

    void initPlayerTime (int time);

    void initMainActivity(TimeControl player1, TimeControl player2);

    void pauseButtonClick();

    void timeIsOut(String losePlayerTag);


//    public void loadClockFragm(TimeControl control);
}
