package com.example.catchessclock.model;

import java.util.ArrayList;
import java.util.List;

public class TimeControl {

    public static final int TYPE_FISHER = -1;
    public static final int TYPE_BRONSH = -2;
    public static final int TYPE_DELAY  = -3;

    public int primaryKey;

    public String title;

    public List<TimeStage> mStageList;

//    public TimeControl() {};

    public TimeControl(String title) {
        this.title = title;
        mStageList = new ArrayList<>();
    }

    public void addStage(int timeLimit, int increment, int incrementType, int turnLimit) {
        TimeStage stage = new TimeStage( timeLimit, increment, incrementType, turnLimit);
        mStageList.add(stage);
    }

    public void addTimeStageByTimelimitAndTurnLimit(int timelimit ,int turnsLimit) {
        if (mStageList.size() != 0) {
            TimeStage stage = new TimeStage(timelimit,
                    mStageList.get(0).increment,
                    mStageList.get(0).incrementType,
                    turnsLimit);
            mStageList.add(stage);
        }
    }

    public void addStage(int timeLimit, int increment, int incrementType) {
        TimeStage stage = new TimeStage( timeLimit, increment, incrementType);
        mStageList.add(stage);
    }

    public TimeStage getTimeStage() {
        TimeStage stage = mStageList.get(0);
        return stage;
    }

    public class TimeStage {
        // time limit in minutes
        public int timeLimit;

        // increment in seconds
        public int increment ;

        // increment type;
        public int incrementType;

        // turn limit
        public int turnLimit;




        public TimeStage ( int timeLimit, int increment, int incrementType) {
            this.timeLimit = timeLimit;
            this.increment = increment;
            this.incrementType = incrementType;
            this.turnLimit = 0;
        }

        public TimeStage ( int timeLimit, int increment, int incrementType, int turnLimit) {
            this.timeLimit = timeLimit;
            this.increment = increment;
            this.incrementType = incrementType;
            this.turnLimit = turnLimit;
        }

    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }
}
