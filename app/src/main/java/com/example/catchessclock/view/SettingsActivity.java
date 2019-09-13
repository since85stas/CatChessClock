package com.example.catchessclock.view;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.interfaces.DatabaseInterface;
import com.example.catchessclock.presenter.DatabasePresenter;
import com.example.catchessclock.presenter.TimingsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SettingsActivity extends MvpAppCompatActivity implements SettingsActivityView, DatabaseInterface {

    @InjectPresenter
    DatabasePresenter mActivityPresenter;

    @ProvidePresenter
    DatabasePresenter mActivityPresenter () {
        return new DatabasePresenter();
    }

    @BindView(R.id.timings_list)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    public void setTimingsList(TimingsAdapter adapter) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }
}
