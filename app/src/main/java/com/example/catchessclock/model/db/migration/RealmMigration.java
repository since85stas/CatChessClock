package com.example.catchessclock.model.db.migration;

import io.realm.DynamicRealm;
import io.realm.RealmSchema;

/**
 * Created by LasVegas on 25.04.2017.
 */

public class RealmMigration implements io.realm.RealmMigration  {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("TaskRealmModel")
                    .addField("title",String.class);

            oldVersion++;
        }
    }

    @Override
    public int hashCode() {
        return RealmMigration.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof RealmMigration);
    }
}
