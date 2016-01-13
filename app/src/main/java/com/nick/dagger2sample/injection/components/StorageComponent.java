package com.nick.dagger2sample.injection.components;

import com.nick.dagger2sample.MainActivity;
import com.nick.dagger2sample.injection.modules.StorageModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {
    void inject(MainActivity activity);
}
