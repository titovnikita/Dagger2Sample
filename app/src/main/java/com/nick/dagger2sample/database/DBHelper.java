package com.nick.dagger2sample.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.nick.dagger2sample.database.models.BaseModel;
import com.nick.dagger2sample.database.tables.DbInfo;
import com.nick.dagger2sample.database.tables.PostsTable;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context) {
        super(context, DbInfo.NAME, null, DbInfo.VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PostsTable.CREATE_POSTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
