package com.nick.dagger2sample.core;

import android.app.Application;
import android.content.Context;

import com.nick.dagger2sample.injection.components.DaggerStorageComponent;
import com.nick.dagger2sample.injection.components.StorageComponent;
import com.nick.dagger2sample.injection.modules.StorageModule;

public class DaggerApplication extends Application {
    private final String API_ENDPOINT = "https://google.com";

    private StorageComponent storageComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        storageComponent = DaggerStorageComponent.builder()
                .storageModule(new StorageModule(this))
                .build();
    }

    public StorageComponent getStorageComponent() {
        return storageComponent;
    }

    public static StorageComponent getStorageComponent(Context context) {
        return ((DaggerApplication) context.getApplicationContext()).getStorageComponent();
    }
}
