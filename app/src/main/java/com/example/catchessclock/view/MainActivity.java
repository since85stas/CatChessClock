package com.example.catchessclock.view;


import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.catchessclock.R;
import com.example.catchessclock.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.InjectViewState;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class MainActivity extends MvpAppCompatActivity implements MainActivityView
{

    @InjectPresenter
    MainActivityPresenter mMainActivityPresenter;

    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHandler = new Handler();

    }

    @Override
    public void initPlayerTime(int time) {
//        mTextView.setText(String.valueOf(time));
    }

    @Override
    public void loadClockFragm(int time) {
        List<Integer> list = new ArrayList<>();
        list.add(time);
        ClockFragment fragment = new ClockFragment(list);

        Runnable  mPendingRunnable = () -> {
            // update the main content by replacing fragments
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.main_container, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }
}
