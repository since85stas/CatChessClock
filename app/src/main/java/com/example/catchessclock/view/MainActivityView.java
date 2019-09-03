package com.example.catchessclock.view;


import moxy.MvpView;

public interface MainActivityView extends MvpView {

    public void initPlayerTime (int time);

    public void loadClockFragm(int time);
}
