package com.nick.dagger2sample.network.requests;

import android.content.Context;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.network.Api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetPostsRequest extends BaseRequest<List<Post>> {

    @Inject
    Api api;

    public GetPostsRequest(Context context, Observer<List<Post>> observer) {
        super(context, observer);
    }

    @Override
    protected void injectApi(Context context) {
        DaggerApplication.getApplicationComponent(context).inject(this);
    }

    @Override
    public Subscription execute() {
        return api.getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)
                .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .subscribe(observer);
    }
}
