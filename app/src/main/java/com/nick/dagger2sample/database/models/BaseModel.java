package com.nick.dagger2sample.database.models;

import android.content.ContentValues;

public abstract class BaseModel {
    public abstract ContentValues toContentValues();

    public abstract void fromContentValues(ContentValues cv);
}
