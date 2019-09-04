package com.example.catchessclock.model;

public class TimeControl {

    public static final int TYPE_FISHER = -1;
    public static final int TYPE_BRONSH = -2;
    public static final int TYPE_DELAY  = -3;

    // time limit in minutes
    public int timeLimit;

    // increment in seconds
    public int increment ;

    // increment type;
    public int incrementType;

    // turn limit
    public int turnLimit;

    public TimeControl ( int timeLimit, int increment, int incrementType) {
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
        this.turnLimit = 0;
    }

    public TimeControl ( int timeLimit, int increment, int incrementType, int turnLimit) {
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
        this.turnLimit = turnLimit;
    }
}
