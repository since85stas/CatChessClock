package com.example.catchessclock.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catchessclock.R;
import com.example.catchessclock.entities.ChessClock;
import com.example.catchessclock.interfaces.ChessClicked;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.ClockFragmentPresenter;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ClockFragment extends MvpAppCompatFragment implements ClockFragmentView {

    public String mTag;

    @InjectPresenter
    ClockFragmentPresenter mClockFragmentPresenter;

    ChessClock mChronometer;

    TimeControl mTimeControl;

    String mName;

    View.OnClickListener mClickListener;

    View.OnClickListener mFirstClickListner;

    ChessClicked clickedInterface;

    View mRootView;

    public ClockFragment(TimeControl timeControl, String tag) {
        mTimeControl = timeControl;
        mTag = tag;

    }


    public void setInterface(ChessClicked clickedInterface) {
        this.clickedInterface = clickedInterface;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.clock_layout, container, false);
        mChronometer = mRootView.findViewById(R.id.time_box);
        setClockInitTimer(mTimeControl);
        setInterface(clickedInterface);

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mChronometer.mIsRunning ) {
                    clickedInterface.chessClockIsClicked(mTag);
//                    mClockFragmentPresenter.pauseTimer();
                }
            }
        };

        mFirstClickListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedInterface.firstChessClockIsClicked(mTag);
            }
        };

        setOnClickListn(mFirstClickListner);
        return mRootView;
    }

    @Override
    public void clockIsPressed() {

    }

    @Override
    public void startTimer() {
        mChronometer.startTimer();
    }

    @Override
    public void pauseTimer() {
        mChronometer.pauseTimer();
    }

    @Override
    public void nextTurn() {
        mChronometer.nextTurn();
    }

    @Override
    public void clockChange() {

    }

    @Override
    public void setClockInitTimer(TimeControl timeControl) {
        mChronometer.setInitState(timeControl);
    }

    public void setOnClickListn(View.OnClickListener listn) {
        mRootView.setOnClickListener(listn);
    }

    public void setInitClickListn() {
        setOnClickListn(mFirstClickListner);
    }

    public void setGameClickListn() {
        setOnClickListn(mClickListener);
    }
}
