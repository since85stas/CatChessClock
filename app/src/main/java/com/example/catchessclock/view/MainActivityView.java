package com.example.catchessclock.view;


import com.example.catchessclock.model.TimeControl;

import moxy.MvpView;

public interface MainActivityView extends MvpView {

    void initPlayerTime (int time);

    void initMainActivity(TimeControl player1, TimeControl player2);


//    public void loadClockFragm(TimeControl control);
}
