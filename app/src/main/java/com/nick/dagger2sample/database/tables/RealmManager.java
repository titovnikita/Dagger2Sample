package com.nick.dagger2sample.database.tables;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmManager {
    public final Realm realm;

    public RealmManager(Realm realm) {
        this.realm = realm;
    }

    public List<? extends RealmObject> getListOf(Class clazz) {
        return realm.where(clazz).findAll();
    }

    public void saveObject(RealmObject realmObject) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
    }

    public void saveList(List<? extends RealmObject> realmObjects) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObjects);
        realm.commitTransaction();
    }

    public void release() {
        realm.close();
    }

}
