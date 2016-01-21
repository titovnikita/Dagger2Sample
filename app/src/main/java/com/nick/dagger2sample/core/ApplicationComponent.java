package com.nick.dagger2sample.core;

import com.nick.dagger2sample.database.DbContentProvider;
import com.nick.dagger2sample.database.StorageModule;
import com.nick.dagger2sample.network.NetworkModule;
import com.nick.dagger2sample.network.loaders.PostsLoader;
import com.nick.dagger2sample.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, StorageModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);

    void inject(DbContentProvider provider);

    void inject(PostsLoader loader);
}