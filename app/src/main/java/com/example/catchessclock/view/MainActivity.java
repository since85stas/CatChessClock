package com.example.catchessclock.view;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.catchessclock.R;
import com.example.catchessclock.interfaces.ChessClicked;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.InjectViewState;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class MainActivity extends MvpAppCompatActivity implements MainActivityView, ChessClicked
{
    public static final String TAG = MainActivity.class.getName();

    public static final String FRAG_TAG1 = "fragm1";
    public static final String FRAG_TAG2 = "fragm2";

    @InjectPresenter
    MainActivityPresenter mMainActivityPresenter;

    private Handler mHandler;

    ClockFragment mClockFragment1;
    ClockFragment mClockFragment2;


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
    public void initMainActivity(TimeControl player1, TimeControl player2) {
        loadClockFragm(player1,R.id.main_container1,FRAG_TAG1);
        loadClockFragm(player2,R.id.main_container2,FRAG_TAG2);
    }

    public void loadClockFragm(TimeControl timeControl, int containerId, String tag) {

        ClockFragment fragment = new ClockFragment(timeControl,tag);
        fragment.mName = String.valueOf(containerId);

        ClockFragment finalFragment = fragment;
        fragment.setInterface(this);

        Runnable  mPendingRunnable = () -> {
            // update the main content by replacing fragments
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(containerId, finalFragment);
            fragmentTransaction.commitAllowingStateLoss();
            switch (tag) {
                case FRAG_TAG1:
                    mClockFragment1 = finalFragment;
                    break;
                case FRAG_TAG2:
                    mClockFragment2 = finalFragment;
                    break;
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    @Override
    public void chessClockIsClicked(String fragmTag) {
        Log.d(TAG, "chessClockIsClicked: " + fragmTag);
        switch (fragmTag){
            case FRAG_TAG1:
                mClockFragment1.mClockFragmentPresenter.pauseTimer();
                mClockFragment2.mClockFragmentPresenter.startTimer();
                break;

            case FRAG_TAG2:
                mClockFragment2.mClockFragmentPresenter.pauseTimer();
                mClockFragment1.mClockFragmentPresenter.startTimer();
                break;
        }
    }

}
