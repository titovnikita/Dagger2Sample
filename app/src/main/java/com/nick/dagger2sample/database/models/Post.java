package com.nick.dagger2sample.database.models;

import android.content.ContentValues;
import android.net.Uri;

import com.nick.dagger2sample.database.tables.PostsTable;

public class Post extends BaseModel {
    private long id;
    private long userId;
    private String title;
    private String body;

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(PostsTable.ID, id);
        cv.put(PostsTable.USER_ID, userId);
        cv.put(PostsTable.TITLE, title);
        cv.put(PostsTable.BODY, body);
        return cv;
    }

    @Override
    public void fromContentValues(ContentValues cv) {
        this.id = cv.getAsLong(PostsTable.ID);
        this.userId = cv.getAsLong(PostsTable.USER_ID);
        this.title = cv.getAsString(PostsTable.TITLE);
        this.body = cv.getAsString(PostsTable.BODY);
    }

    @Override
    public Uri getContentUri() {
        return PostsTable.CONTENT_URI;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
