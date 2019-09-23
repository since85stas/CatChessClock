package com.example.catchessclock.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.catchessclock.R;
import com.example.catchessclock.entities.ChessClock;
import com.example.catchessclock.interfaces.ChessClicked;
import com.example.catchessclock.interfaces.DatabaseInterface;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.DatabasePresenter;
import com.example.catchessclock.presenter.MainActivityPresenter;
import com.example.catchessclock.presenter.TimingsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.InjectViewState;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class MainActivity extends MvpAppCompatActivity implements MainActivityView, ChessClicked, ChessClock.ChessClockInterface

{
    public static final String TAG = MainActivity.class.getName();

    public static final String FRAG_TAG1 = "fragm1";
    public static final String FRAG_TAG2 = "fragm2";

    @InjectPresenter
    MainActivityPresenter mMainActivityPresenter;

    private Handler mHandler;

    ClockFragment mClockFragment1;
    ClockFragment mClockFragment2;

    @BindView(R.id.pause_button)
    Button mPauseButton;

    @OnClick ({R.id.settings_button, R.id.pause_button, R.id.reset_button})
    void onButtonClick (View view) {
        switch (view.getId()) {
            case R.id.settings_button:
                Intent openInt = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(openInt);
                break;
            case R.id.pause_button:
                pauseButtonClick();
                break;
            case R.id.reset_button:
                Intent resetInt = new Intent(MainActivity.this, MainActivity.class);
                startActivity(resetInt);
                break;
        }
    }

    @Override
    public void finishTimer(String str) {
        Log.d(TAG, "finishTimer: ");
        mMainActivityPresenter.timeIsOut(str);
    }

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

    @Override
    protected void onStop() {
        pauseButtonClick();
        super.onStop();
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
                mClockFragment1.mClockFragmentPresenter.nextTurn();
                mClockFragment2.mClockFragmentPresenter.startTimer();
                break;

            case FRAG_TAG2:
                mClockFragment2.mClockFragmentPresenter.nextTurn();
                mClockFragment1.mClockFragmentPresenter.startTimer();
                break;
        }
    }


    @Override
    public void firstChessClockIsClicked(String fragmTag) {
        mPauseButton.setVisibility(View.VISIBLE);
        switch (fragmTag){
            case FRAG_TAG1:
                mClockFragment1.mClockFragmentPresenter.startTimer();
                break;

            case FRAG_TAG2:
                mClockFragment2.mClockFragmentPresenter.startTimer();
                break;
        }
        mClockFragment1.setGameClickListn();
        mClockFragment2.setGameClickListn();
    }

    @Override
    public void pauseButtonClick() {
        mClockFragment1.mClockFragmentPresenter.pauseTimer();
        mClockFragment1.setInitClickListn();
        mClockFragment2.mClockFragmentPresenter.pauseTimer();
        mClockFragment2.setInitClickListn();
    }

    @Override
    public void timeIsOut(String losePlayerTag) {
        switch (losePlayerTag) {
            case FRAG_TAG1:
                mClockFragment1.mClockFragmentPresenter.setFragmentLooseState();
                break;
            case FRAG_TAG2:
                mClockFragment2.mClockFragmentPresenter.setFragmentLooseState();
                break;
        }

    }

    //
//    @Override
//    public void setTimingsList(TimingsAdapter adapter) {
//
//    }
//
//    @Override
//    public TimeControl getCurrentTiming() {
//        return null;
//    }
}
