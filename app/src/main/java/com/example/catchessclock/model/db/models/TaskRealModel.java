package com.example.catchessclock.model.db.models;

import androidx.annotation.NonNull;

import com.example.catchessclock.model.TimeControl;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by LasVegas on 25.04.2017.
 */

public class TaskRealModel extends RealmObject {

    @PrimaryKey
    private long id;

    private String title;

    private float timeLimit;

    private int increment;

    private int incrementType;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setModel(String title, float timeLimit, int increment, int incrementType) {
        this.title = title;
        this.timeLimit = timeLimit;
        this.increment = increment;
        this.incrementType = incrementType;
    }

    public float getTimeLimit() {
        return timeLimit;
    }

    public int getIncrement() {
        return increment;
    }

    public int getIncrementType() {
        return incrementType;
    }

    public void setTimeLimit(float timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public void setIncrementType(int incrementType) {
        this.incrementType = incrementType;
    }

//    @Override
//    public boolean equals(Object obj) {
//        TaskRealModel newObj = new TaskRealModel();
//        newObj.setModel(obj.);
//        return super.equals(obj);
//    }


    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        TaskRealModel newObj = new TaskRealModel();
        newObj.setModel(title,timeLimit,increment,incrementType);
        return newObj;
    }

    public TimeControl getTimeControl() {
        TimeControl control = new TimeControl(timeLimit,increment,incrementType,title);
        control.setPrimaryKey((int)id);
        return control;
    }
}
