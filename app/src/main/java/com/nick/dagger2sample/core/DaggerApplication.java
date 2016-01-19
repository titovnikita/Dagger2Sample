package com.nick.dagger2sample.core;

import android.app.Application;
import android.content.Context;

import com.nick.dagger2sample.database.StorageModule;
import com.nick.dagger2sample.network.NetworkModule;

public class DaggerApplication extends Application {
    private final String API_ENDPOINT = "http://jsonplaceholder.typicode.com";

    private ApplicationComponent applicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        applicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule(API_ENDPOINT))
                .storageModule(new StorageModule(this))
                .build();
    }

    private ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((DaggerApplication) context.getApplicationContext()).getApplicationComponent();
    }
}
