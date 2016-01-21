package com.nick.dagger2sample.database;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.models.BaseModel;
import com.nick.dagger2sample.database.tables.RealmManager;
import com.nick.dagger2sample.utils.SharedHelper;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Singleton
@Module
public class StorageModule {

    private final DaggerApplication application;

    public StorageModule(DaggerApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    public SharedHelper provideSharedPreferencesHelper(SharedPreferences sharedPreferences) {
        return new SharedHelper(sharedPreferences);
    }

    @Singleton
    @Provides
    public DBHelper provideDatabaseHelper() {
        return new DBHelper(application.getBaseContext());
    }

    @Provides
    public Realm provideRealm() {
        return Realm.getInstance(application);
    }

    @Provides
    public RealmManager provideRealmManager(Realm realm) {
        return new RealmManager(realm);
    }
}
