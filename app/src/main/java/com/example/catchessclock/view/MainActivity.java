package com.example.catchessclock.view;


import android.os.Bundle;
import android.widget.TextView;

import com.example.catchessclock.R;
import com.example.catchessclock.presenter.MainActivityPresenter;

import butterknife.BindView;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class MainActivity extends MvpAppCompatActivity implements MainActivityView
{
    @InjectPresenter
    MainActivityPresenter mMainActivityPresenter;

    @BindView(R.id.helloW)
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initPlayerTime(int time) {
        mTextView.setText(time);
    }
}
