package com.nick.dagger2sample.database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmManager {
    public final Context context;

    public RealmManager(Context context) {
        this.context = context;
    }

    private Realm getRealm() {
        return Realm.getInstance(context);
    }

    public List<? extends RealmObject> getListOf(Class clazz) {
        return getRealm().where(clazz).findAll();
    }

    public void saveObject(RealmObject realmObject) {
        Realm realm = getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObject);
        realm.commitTransaction();
        realm.close();
    }

    public void saveList(List<? extends RealmObject> realmObjects) {
        Realm realm = getRealm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmObjects);
        realm.commitTransaction();
        realm.close();
    }
}
