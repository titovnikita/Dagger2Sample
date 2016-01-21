package com.nick.dagger2sample.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.nick.dagger2sample.database.models.BaseModel;
import com.nick.dagger2sample.database.tables.DbInfo;
import com.nick.dagger2sample.database.tables.PostsTable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DbInfo.NAME, null, DbInfo.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PostsTable.CREATE_POSTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void bulkInsert(Context context, List<? extends BaseModel> objects) {
        for (BaseModel obj : objects) {
            context.getContentResolver().insert(obj.getContentUri(), obj.toContentValues());
        }
    }

    public static <T extends BaseModel> List<T> queryAll(Context context, Uri contentUri, Class<T> tClass) {
        List<T> objects = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    objects.add(tClass.newInstance().<T>fromCursor(cursor));
                }
            } catch (InstantiationException exception) {
                exception.printStackTrace();
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
        return objects;
    }
}
