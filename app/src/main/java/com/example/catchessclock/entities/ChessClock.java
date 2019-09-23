package com.example.catchessclock.entities;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import com.example.catchessclock.model.TimeControl;

public class ChessClock extends Chronometer {

    public interface ChessClockInterface {
        public void finishTimer(String tag);
    }

    //    public ChessClock (){};
    public static final String TAG = ChessClock.class.getName();

    public String mFragmTag;

    public boolean mIsRunning;

    public int mIncrType;

    public long mStartTime;

    public long mTimeLimit;

    public int mIncrement;

    public ChessClockInterface mFinishListner;


    public ChessClock(Context context) {
        super(context);
        mFinishListner = (ChessClockInterface)context;
    }

    public ChessClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFinishListner = (ChessClockInterface)context;
    }

    public ChessClock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mFinishListner = (ChessClockInterface)context;
    }

    public void setTag(String tag) {
        mFragmTag = tag;
    }

    public void setInitState(TimeControl timeControl) {
        this.mTimeLimit = (long)(timeControl.getTimeStage().timeLimit * 1000);
        mIncrement = timeControl.getTimeStage().increment * 1000;
        mIncrType  = timeControl.getTimeStage().incrementType;
        setCountDown(true);
        mStartTime = SystemClock.elapsedRealtime() + mTimeLimit;
        setBase(mStartTime);
    }

    public void startTimer() {

        mIsRunning = true;
        mStartTime = SystemClock.elapsedRealtime() + mTimeLimit;
        setBase(mStartTime);
        initTickListner();
        start();
    }

//    @Override
//    public void setOnChronometerTickListener(OnChronometerTickListener listener) {
//        super.setOnChronometerTickListener(listener);
//    }

    public void addIncrement() {
        if (mIncrement != 0) {
            switch (mIncrType) {
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

    public void nextTurn() {
        addIncrement();
        mIsRunning = false;
        long time = SystemClock.elapsedRealtime();
        stop();
    }

    public void pauseTimer() {
        mIsRunning = false;
        long time = SystemClock.elapsedRealtime();
        stop();
    }

    private void resetTimer() {

    }

    private void initTickListner() {
        setOnChronometerTickListener(listner -> {

            mTimeLimit = mStartTime - SystemClock.elapsedRealtime();
            if (mTimeLimit < 0) {
                Log.d(TAG, "startTimer: stop");
                mFinishListner.finishTimer(mFragmTag);
            }
        });
    }

    public void changeDrawState() {

    }

}
