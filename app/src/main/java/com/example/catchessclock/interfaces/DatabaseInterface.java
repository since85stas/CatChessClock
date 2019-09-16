package com.example.catchessclock.interfaces;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.TimingsAdapter;

import moxy.MvpView;

public interface DatabaseInterface extends MvpView {
    void setTimingsList(TimingsAdapter adapter);
    void initCurrentCheckBox();
//    TimeControl getCurrentTiming();
}
