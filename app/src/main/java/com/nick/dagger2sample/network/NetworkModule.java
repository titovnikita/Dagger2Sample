package com.nick.dagger2sample.network;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Singleton
@Module
public class NetworkModule {

    private final String API_ENDPOINT;

    public NetworkModule(String API_ENDPOINT) {
        this.API_ENDPOINT = API_ENDPOINT;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }

    @Singleton
    @Provides
    public Retrofit provideRestAdapter(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

}
