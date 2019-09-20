package com.example.catchessclock.view;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.TimingsSelectAdapter;

import moxy.MvpView;

public interface TimeControlActivityView extends MvpView {
    void saveButtonClicked();

    void stageRedactorClicked();

    void incrementRedactorClicked();

    void setActivityState(TimeControl control);

    void setTimingsSelectList(TimingsSelectAdapter adapter);
}
