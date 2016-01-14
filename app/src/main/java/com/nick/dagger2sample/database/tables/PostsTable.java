package com.nick.dagger2sample.database.tables;

import android.net.Uri;

public class PostsTable {
    public static final String TABLE_NAME = "posts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + DbInfo.AUTHORITY + "/" + TABLE_NAME);

    public static final String ID = "_id";
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String BODY = "body";

    public static final String CREATE_POSTS_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY UNIQUE,"
            + USER_ID + " INTEGER,"
            + TITLE + " TEXT,"
            + BODY + " TEXT"
            + ")";

}
