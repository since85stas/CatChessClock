package com.example.catchessclock.model.db.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TimeStageModel extends RealmObject {

    public TimeStageModel (long modelId) {
        this.modelId = modelId;
    };

    public TimeStageModel() {}


    private int timeLimit;

    private int increment;

    private int incrementType;

    private int turnLimit;

    private long modelId;

    public void setModel(int timeLimit, int increment, int incrementType, int turnLimit) {
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
        this.turnLimit     = turnLimit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getIncrement() {
        return increment;
    }

    public int getIncrementType() {
        return incrementType;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public void setIncrementType(int incrementType) {
        this.incrementType = incrementType;
    }

    public int getTurnLimit() {
        return turnLimit;
    }

}
