package com.nick.dagger2sample.network.loaders;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.nick.dagger2sample.utils.RxUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public abstract class BaseLoader<T> extends Loader<T> {

    protected final long RETRY_COUNT = 3;
    protected final long TIMEOUT_SECONDS = 3;

    private T cache;
    private Subscription subscription;

    public BaseLoader(Context context) {
        super(context);

        injectApi(context);
    }

    //Possibility to pass page number when implementing pagination;
    public void loadNewData() {
        forceLoad();
    }

    @Override
    protected void onStartLoading() {
        if (cache == null) {
            forceLoad();
        } else {
            deliverResult(cache);
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        RxUtils.unsubscribe(subscription);
        subscription = callApi()
                .subscribe(new Observer<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getClass().getSimpleName(), e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(T data) {
                        deliverResult(data);
                    }
                });
    }

    @Override
    protected void onReset() {
        super.onReset();
        RxUtils.unsubscribe(subscription);
    }

    @Override
    public void deliverResult(T data) {
        cache = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    protected abstract Observable callApi();

    protected abstract void injectApi(Context context);
}
