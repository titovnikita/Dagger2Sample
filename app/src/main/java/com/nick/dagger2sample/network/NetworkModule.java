package com.nick.dagger2sample.network;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    public Retrofit provideRestAdapter() {
        return new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

}
