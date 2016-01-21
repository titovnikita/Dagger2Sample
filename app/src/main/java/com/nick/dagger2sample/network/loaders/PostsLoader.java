package com.nick.dagger2sample.network.loaders;

import android.content.Context;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.network.Api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

public class PostsLoader extends BaseLoader<List<Post>> {

    @Inject
    Api api;

    public PostsLoader(Context context) {
        super(context);
    }

    @Override
    protected void injectApi(Context context) {
        DaggerApplication.getApplicationComponent(context).inject(this);
    }

    @Override
    protected Observable callApi() {
        return api.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .retry(RETRY_COUNT)
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

}
