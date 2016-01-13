package com.nick.dagger2sample.database;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {
}
