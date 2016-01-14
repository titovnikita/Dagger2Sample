package com.nick.dagger2sample.ui.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.widget.Button;
import android.widget.ListView;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.tables.PostsTable;
import com.nick.dagger2sample.network.requests.GetPostsRequest;
import com.nick.dagger2sample.network.requests.RequestExecutor;
import com.nick.dagger2sample.ui.adapters.PostsAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Response;

public class MainActivity extends BaseActivity implements RequestExecutor.OnRequestExecuted {

    private static final int LOADER_ID_ITEM = 1;

    @Inject
    RequestExecutor requestExecutor;
    @Bind(R.id.btnGetPosts)
    Button btnGetPosts;
    @Bind(R.id.lvPosts)
    ListView lvPosts;

    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerApplication.getApplicationComponent(this).inject(this);

        adapter = new PostsAdapter(this, getContentResolver().query(PostsTable.CONTENT_URI, null, null, null, null), true);
        lvPosts.setEmptyView(findViewById(R.id.tvEmptyView));
        lvPosts.setAdapter(adapter);
        getSupportLoaderManager().initLoader(LOADER_ID_ITEM, Bundle.EMPTY, new PostsLoaderCallback());
    }

    @OnClick(R.id.btnGetPosts)
    void onGetPostsButtonClick() {
        requestExecutor.execute(new GetPostsRequest(this), this);
    }

    @Override
    public void onSuccess(String requestType, Response response) {
        switch (requestType) {
            case GetPostsRequest.REQUEST_TYPE:
                makeToast(getString(R.string.posts_uploaded));
                break;
        }
    }

    @Override
    public void onFailure(String requestType, int code) {
        switch (requestType) {
            case GetPostsRequest.REQUEST_TYPE:
                makeToast(getString(R.string.error));
                break;
        }
    }


    private class PostsLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(MainActivity.this, PostsTable.CONTENT_URI, null,
                    null, null, null);
        }

        @Override
        public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
            adapter.changeCursor(data);
        }

        @Override
        public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
            adapter.changeCursor(null);
        }
    }
}
