package com.nick.dagger2sample.network;

import com.nick.dagger2sample.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    void inject(MainActivity activity);
}
