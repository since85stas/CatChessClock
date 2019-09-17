package com.example.catchessclock.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.interfaces.DatabaseInterface;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.DatabasePresenter;
import com.example.catchessclock.presenter.SettingsPresenter;
import com.example.catchessclock.presenter.TimingsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SettingsActivity extends MvpAppCompatActivity implements SettingsActivityView, DatabaseInterface {

    @BindView(R.id.timings_list)
    RecyclerView mRecyclerView;

    TimingsAdapter mAdapter;

    @InjectPresenter
    DatabasePresenter mDataPresenter;

//    @ProvidePresenter
//    DatabasePresenter mActivityPresenter () {
//
//        return new DatabasePresenter();
//    }

    @InjectPresenter
    SettingsPresenter mSettingsPresenter;

    @ProvidePresenter
    SettingsPresenter mSettingsPresenter () {
        SettingsPresenter presenter = new SettingsPresenter();
//        presenter.setREscycle(mRecyclerView);
        return presenter;
    }

    @OnClick(R.id.start_button)
    void onStartClick() {
        mSettingsPresenter.startButtonClicked();
    }

    @OnClick(R.id.flaotingButt)
    void onAddClick() {
        mSettingsPresenter.addButtonClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    public void setTimingsList(TimingsAdapter adapter) {
        mAdapter = adapter;
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        adapter.setRecycler(mRecyclerView);
        mRecyclerView.setAdapter(adapter);
//        mAdapter.initCheckBox(mDataPresenter.getCurrTimingId());
//        FloatingActionButton button = findViewById(R.id.);
    }

    @Override
    public void startButtonPreesd() {
        mDataPresenter.saveCurrentTiming(mAdapter.getSelectedTime().primaryKey);
        Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
        startActivity(intent);
        Log.d("sett", "startButtonPreesd: ");
    }


    @Override
    protected void onStop() {
        super.onStop();
        mDataPresenter.saveCurrentTiming(mAdapter.getSelectedTime().primaryKey);
    }

    @Override
    public void addButtonCliked() {
        mDataPresenter.saveCurrentTiming(mAdapter.getSelectedTime().primaryKey);
        Intent intent = new Intent(SettingsActivity.this,TimeControlActivity.class);
        startActivity(intent);
    }
}
