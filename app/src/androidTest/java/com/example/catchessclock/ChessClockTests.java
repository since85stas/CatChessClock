package com.example.catchessclock;

import android.app.Application;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static org.hamcrest.MatcherAssert.assertThat;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;

import com.example.catchessclock.entities.ChessClock;
import com.example.catchessclock.model.TimeControl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import static org.assertj.*;

@RunWith(AndroidJUnit4.class)
public class ChessClockTests {

    TimeControl mTimeControl;
    ChessClock mChessClock;

    @Before
    public void init() {
        mTimeControl = new TimeControl("test");
        mTimeControl.setPrimaryKey(0);
        mTimeControl.addStage(10,5,-1,50);
        mChessClock = new ChessClock(ApplicationProvider.getApplicationContext());
        mChessClock.setInitState(mTimeControl);

    }

    @Test
    public void check_chessClock_init() {
//        assertThat("testing time", mChessClock.);
    }



}
