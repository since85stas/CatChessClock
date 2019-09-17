package com.example.catchessclock.presenter;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.view.SettingsActivity;
import com.example.catchessclock.view.SettingsActivityView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsActivityView> {

//    public void SettingsPresenter() {
//
//    }

    @Override
    public void attachView(SettingsActivityView view) {

        super.attachView(view);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }


    public void startButtonClicked() {
        getViewState().startButtonPreesd();
    }

    public void addButtonClicked() {
        getViewState().addButtonCliked();
    }

    public void log() {
        Log.d("pres", "log: ");
    }
}
