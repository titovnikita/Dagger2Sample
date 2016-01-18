package com.nick.dagger2sample.ui.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.DBHelper;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.database.tables.PostsTable;
import com.nick.dagger2sample.network.requests.GetPostsRequest;
import com.nick.dagger2sample.ui.adapters.PostsAdapter;
import com.nick.dagger2sample.utils.RxUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Response;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {

    private static final int LOADER_ID_ITEM = 1;

    @Bind(R.id.btnStartInput)
    Button btnStartInput;
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

    @OnClick(R.id.btnStartInput)
    void onStartInputButtonClick() {
        startActivity(new Intent(this, InputActivity.class));
    }

    @OnClick(R.id.btnGetPosts)
    void onGetPostsButtonClick() {
        addSubscription(new GetPostsRequest(this, postsObserver).execute());
    }


    Observer<List<Post>> postsObserver = new Observer<List<Post>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e(getClass().getSimpleName(), e.toString());
        }

        @Override
        public void onNext(List<Post> posts) {
            DBHelper.bulkInsert(getBaseContext(), posts);
        }
    };

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
