package com.nick.dagger2sample.core;

import android.app.Application;
import android.content.Context;

import com.nick.dagger2sample.database.DaggerStorageComponent;
import com.nick.dagger2sample.database.StorageComponent;
import com.nick.dagger2sample.database.StorageModule;
import com.nick.dagger2sample.network.DaggerNetworkComponent;
import com.nick.dagger2sample.network.NetworkComponent;
import com.nick.dagger2sample.network.NetworkModule;

public class DaggerApplication extends Application {
    private final String API_ENDPOINT = "http://jsonplaceholder.typicode.com/";

    private StorageComponent storageComponent;
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(API_ENDPOINT))
                .build();
        storageComponent = DaggerStorageComponent.builder()
                .storageModule(new StorageModule(this))
                .build();
    }

    public StorageComponent getStorageComponent() {
        return storageComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public static StorageComponent getStorageComponent(Context context) {
        return ((DaggerApplication) context.getApplicationContext()).getStorageComponent();
    }

    public static NetworkComponent getNetworkComponent(Context context) {
        return ((DaggerApplication) context.getApplicationContext()).getNetworkComponent();
    }
}
