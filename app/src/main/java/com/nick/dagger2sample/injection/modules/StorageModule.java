package com.nick.dagger2sample.injection.modules;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.utils.SharedHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class StorageModule {

    private DaggerApplication application;

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
    public SharedHelper provideSharedPreferencesHelper(SharedPreferences sharedPreferences){
        return new SharedHelper(sharedPreferences);
    }
}
