package com.example.catchessclock.entities;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import com.example.catchessclock.model.TimeControl;

public class ChessClock extends Chronometer {

    //    public ChessClock (){};
    public static final String TAG = ChessClock.class.getName();

    public boolean mIsRunning;

    TimeControl mTimeControl;

    long mStartTime;

    long mTimeLimit;

    int mIncrement;


    public ChessClock(Context context) {
        super(context);
    }

    public ChessClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChessClock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setInitState(TimeControl timeControl) {
        this.mTimeLimit = timeControl.timeLimit * 60 * 1000;
        mIncrement = timeControl.increment * 1000;
        setCountDown(true);

    }

    public void startTimer() {
        addIncrement();
        mIsRunning = true;
        mStartTime = SystemClock.elapsedRealtime() + mTimeLimit;
        setBase(mStartTime);
        initTickListner();
        start();
    }

    public void addIncrement() {
        if (mIncrement != 0) {
            switch (mTimeControl.incrementType) {
                case TimeControl.TYPE_FISHER:
                    mTimeLimit += mIncrement;
                    break;

                case TimeControl.TYPE_BRONSH:

                    break;

                case TimeControl.TYPE_DELAY:

                    break;
            }
        }
    }

    public void pauseTimer() {
        mIsRunning = false;
        long time = SystemClock.elapsedRealtime();
//        setBase(SystemClock.elapsedRealtime());
        stop();
    }

    private void resetTimer() {

    }



    private void initTickListner() {
        setOnChronometerTickListener(listner -> {

            mTimeLimit = mStartTime - SystemClock.elapsedRealtime();
            if (mTimeLimit < 0) {
                Log.d(TAG, "startTimer: stop");
            }
        });
    }

}
