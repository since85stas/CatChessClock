package com.example.catchessclock.model;

public class TimeControl {

    public static final int TYPE_FISHER = -1;
    public static final int TYPE_BRONSH = -2;
    public static final int TYPE_DELAY  = -3;

    // time limit in minutes
    public float timeLimit;

    // increment in seconds
    public int increment ;

    // increment type;
    public int incrementType;

    // turn limit
    public int turnLimit;

    public String title;

    public TimeControl ( float timeLimit, int increment, int incrementType, String title) {
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
        this.turnLimit = 0;
        this.title = title;
    }

    public TimeControl ( float timeLimit, int increment, int incrementType, int turnLimit, String title) {
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
        this.turnLimit = turnLimit;
        this.title = title;
    }
}
