package com.nick.dagger2sample.network.requests;

import android.util.Log;

import com.nick.dagger2sample.network.Api;

import retrofit.Response;

public abstract class BaseRequest {
    private static final String TAG = "REQUEST";

    public abstract String getRequestType();

    public abstract Response execute(Api api);

    public void log(String message) {
        Log.d(TAG, message);
    }
}
