package com.example.catchessclock.model.db.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CurrentTimingsModel extends RealmObject {

    public static int ID = 0;

    @PrimaryKey
    int id;

    int selectTimingsId;

    public CurrentTimingsModel() {};

    public CurrentTimingsModel (int id) {
        selectTimingsId = id;
        id = ID;
    }

    public int getSelectTimingsId() {
        return selectTimingsId;
    }
}
