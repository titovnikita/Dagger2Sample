package com.nick.dagger2sample.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.tables.DbInfo;
import com.nick.dagger2sample.database.tables.PostsTable;

import javax.inject.Inject;

public class DbContentProvider extends ContentProvider {

    private static final int POSTS = 1;
    private static final int POSTS_POST = 2;

    private static final String POSTS_TABLE_NAME = PostsTable.TABLE_NAME;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(DbInfo.AUTHORITY, POSTS_TABLE_NAME, POSTS);
        URI_MATCHER.addURI(DbInfo.AUTHORITY, POSTS_TABLE_NAME + "/#", POSTS_POST);
    }

    @Inject
    DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        DaggerApplication.getApplicationComponent(getContext()).inject(this);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        String selectionToAppend = "";
        if (isItem(URI_MATCHER.match(uri))) {
            selectionToAppend = getSelectionToAppend(uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(tableName, projection, appendSelections(selection, selectionToAppend),
                selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        final String DIR = "vnd.android.cursor.dir/";
        final String ITEM = "vnd.android.cursor.item/";
        String result = "";
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                result += DIR + DbInfo.AUTHORITY + "." + POSTS_TABLE_NAME;
                break;
            case POSTS_POST:
                result += ITEM + DbInfo.AUTHORITY + "." + POSTS_TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long result;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        result = db.insertWithOnConflict(getTableName(uri), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.withAppendedPath(uri, String.valueOf(result));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        String selectionToAppend = "";
        if (isItem(URI_MATCHER.match(uri))) {
            selectionToAppend = getSelectionToAppend(uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int affected = db.delete(tableName, appendSelections(selection, selectionToAppend), selectionArgs);
        if (affected > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        String selectionToAppend = "";
        if (isItem(URI_MATCHER.match(uri))) {
            selectionToAppend = getSelectionToAppend(uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int affected = db.update(tableName, values, appendSelections(selection, selectionToAppend), selectionArgs);
        if (affected > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return affected;
    }

    private boolean isItem(int uriItem) {
        return uriItem == POSTS_POST;
    }

    private String appendSelections(String baseSelection, String selectionToAppend) {
        if (!TextUtils.isEmpty(selectionToAppend)) {
            if (!TextUtils.isEmpty(baseSelection)) {
                baseSelection = " ( " + baseSelection + " ) AND " + selectionToAppend;
            } else {
                baseSelection = selectionToAppend;
            }
        }
        return baseSelection;
    }

    private String getTableName(Uri uri) {
        String tableName = "";
        switch (URI_MATCHER.match(uri)) {
            case POSTS:
                tableName = POSTS_TABLE_NAME;
                break;
            case POSTS_POST:
                tableName = POSTS_TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return tableName;
    }

    private String getSelectionToAppend(Uri uri) {
        String selectionToAppend = "";
        switch (URI_MATCHER.match(uri)) {
            case POSTS_POST:
                selectionToAppend = PostsTable.ID + " = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return selectionToAppend;
    }

}
