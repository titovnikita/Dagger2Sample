package com.nick.dagger2sample.network.requests;

import android.os.Handler;
import android.os.Looper;

import com.nick.dagger2sample.network.Api;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.Response;

public class RequestExecutor {
    private final int THREAD_POOL_COUNT = 3;

    private Executor executor;
    private Api api;

    public RequestExecutor(Api api) {
        this.api = api;
        this.executor = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
    }

    public void execute(final BaseRequest request, final OnRequestExecuted listener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final Response response = request.execute(api);

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response == null) {
                            listener.onFailure(request.getRequestType(), 0);
                        } else if (!response.isSuccess()) {
                            listener.onFailure(request.getRequestType(), response.code());
                        } else {
                            listener.onSuccess(request.getRequestType(), response);
                        }
                    }
                });
            }
        });
    }

    public interface OnRequestExecuted {
        void onSuccess(String requestType, final Response response);

        void onFailure(String requestType, int code);
    }
}
