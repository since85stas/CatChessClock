package com.example.catchessclock.view;

import com.example.catchessclock.model.TimeControl;

import moxy.MvpView;

public interface TimeControlActivityView extends MvpView {
    void saveButtonClicked();

    void stageRedactorClicked();

    void incrementRedactorClicked();

    void setActivityState(TimeControl control);
}
