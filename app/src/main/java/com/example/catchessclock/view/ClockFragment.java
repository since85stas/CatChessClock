package com.example.catchessclock.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catchessclock.R;
import com.example.catchessclock.entities.ChessClock;
import com.example.catchessclock.interfaces.ChessClicked;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.ClockFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ClockFragment extends MvpAppCompatFragment implements ClockFragmentView {

    public String mTag;

    @InjectPresenter
    ClockFragmentPresenter mClockFragmentPresenter;

    List<Integer> mGameTime;

    ChessClock mChronometer;

    TimeControl mTimeControl;

    String mName;

    View.OnClickListener mClickListener;

    ChessClicked clickedInterface;

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

        View rootView = inflater.inflate(R.layout.clock_layout, container, false);
        mChronometer = rootView.findViewById(R.id.time_box);
        setClockInitTimer(mTimeControl);
        setInterface(clickedInterface);

        if(mTag == MainActivity.FRAG_TAG1) {
            startTimer();
        }

        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mChronometer.mIsRunning ) {
                    clickedInterface.chessClockIsClicked(mTag);
//                    mClockFragmentPresenter.pauseTimer();
                } else {
//                    mClockFragmentPresenter.startTimer();
                }
            }
        };
        rootView.setOnClickListener(mClickListener);
        return rootView;
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
    public void clockChange() {

    }

    @Override
    public void setClockInitTimer(TimeControl timeControl) {
        mChronometer.setInitState(timeControl);
    }
}
