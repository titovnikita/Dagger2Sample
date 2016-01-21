package com.nick.dagger2sample.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public abstract class BaseModel {
    public abstract ContentValues toContentValues();

    public abstract <T extends BaseModel> T fromCursor(Cursor cursor);

    public abstract Uri getContentUri();

    public abstract BaseModel getInstance();
}
