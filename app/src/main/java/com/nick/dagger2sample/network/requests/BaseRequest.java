package com.nick.dagger2sample.network.requests;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.nick.dagger2sample.database.models.BaseModel;
import com.nick.dagger2sample.network.Api;

import java.util.List;

import retrofit.Response;

public abstract class BaseRequest {
    private static final String TAG = "REQUEST";

    private Context context;

    public BaseRequest(Context context) {
        this.context = context;
    }

    protected void saveToDatabase(final List<? extends BaseModel> list, final Uri contentUri) {
        for (BaseModel item : list) {
            context.getContentResolver().insert(contentUri, item.toContentValues());
        }
    }

    protected void saveToDatabase(final BaseModel item, final Uri contentUri) {
        context.getContentResolver().insert(contentUri, item.toContentValues());
    }

    public abstract String getRequestType();

    public abstract Response execute(Api api);

    public void log(String message) {
        Log.d(TAG, message);
    }
}
