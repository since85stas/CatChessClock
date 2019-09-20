package com.example.catchessclock.model.db.models;

import androidx.annotation.NonNull;

import com.example.catchessclock.model.TimeControl;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by LasVegas on 25.04.2017.
 */

public class TaskRealModel extends RealmObject {

    @PrimaryKey
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public RealmList<TimeStageModel> mStageList;

    public TaskRealModel() {

//        mStageList = new RealmList<TimeStageModel>();
    }



    public void addStage( int timeLimit, int increment, int incrementType) {
        TimeStageModel model = new TimeStageModel();
        model.setModel(timeLimit,increment,incrementType);
    }

//    public void addStages (List<TimeControl.TimeStage> stages) {
//        for (int i = 0; i < stages.size(); i++) {
//            TimeStageModel timeStageModel = new TimeStageModel();
//            timeStageModel.setModel(
//                    stages.get(i).timeLimit,
//                    stages.get(i).increment,
//                    stages.get(i).incrementType);
//            mStageList.add(timeStageModel);
//        }
//    }
//
//    public void addStagesModel(List<TimeStageModel> model) {
//        for (int i = 0; i < model.size(); i++) {
//            mStageList.add(model.get(i));
//        }
//    }

//    public void addStage(int timeLimit, int increment, int incrementType, int turnLimit, String title) {
//        TimeControl.TimeStage stage = new TimeControl.TimeStage( timeLimit, increment, incrementType, turnLimit,title);
//        mStageList.add(stage);
//    }
//
//    public void addStage(int timeLimit, int increment, int incrementType, String title) {
//        TimeControl.TimeStage stage = new TimeControl.TimeStage( timeLimit, increment, incrementType,title);
//        mStageList.add(stage);
//    }




//    @Override
//    public boolean equals(Object obj) {
//        TaskRealModel newObj = new TaskRealModel();
//        newObj.setModel(obj.);
//        return super.equals(obj);
//    }


//    @NonNull
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        TaskRealModel newObj = new TaskRealModel();
//        newObj.setId(id);
//        newObj.addStagesModel(mStageList);
//        return newObj;
//    }
//
//    public TimeControl getTimeControl() {
//        TimeControl control = new TimeControl(title);
//        control.setPrimaryKey((int)id);
//        for (int i = 0; i < mStageList.size(); i++) {
//            control.addStage(
//                    mStageList.get(i).getTimeLimit(),
//                    mStageList.get(i).getIncrement(),
//                    mStageList.get(i).getIncrementType()
//            );
//        }
//        return control;
//    }
}
