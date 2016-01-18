package com.nick.dagger2sample.network.requests;

import android.content.Context;

import rx.Observer;
import rx.Subscription;

public abstract class BaseRequest<T> {
    private static final String TAG = "REQUEST";
    protected final long RETRY_COUNT = 3;
    protected final long TIMEOUT_SECONDS = 3;

    protected Observer<T> observer;

    public BaseRequest(Context context, Observer<T> observer) {
        this.observer = observer;

        injectApi(context);
    }

    protected abstract void injectApi(Context context);

    public abstract Subscription execute();
}
