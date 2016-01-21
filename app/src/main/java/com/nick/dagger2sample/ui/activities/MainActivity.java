package com.nick.dagger2sample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Button;
import android.widget.ListView;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.DBHelper;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.database.tables.PostsTable;
import com.nick.dagger2sample.network.loaders.PostsLoader;
import com.nick.dagger2sample.ui.adapters.PostsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final int LOADER_ID_ITEM = 1;

    @Bind(R.id.btnStartInput)
    Button btnStartInput;
    @Bind(R.id.btnGetPosts)
    Button btnGetPosts;
    @Bind(R.id.lvPosts)
    ListView lvPosts;

    private PostsAdapter adapter;
    private PostsLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerApplication.getApplicationComponent(this).inject(this);

        adapter = new PostsAdapter(this, DBHelper.queryAll(this, PostsTable.CONTENT_URI, Post.class));
        lvPosts.setEmptyView(findViewById(R.id.tvEmptyView));
        lvPosts.setAdapter(adapter);
        loader = (PostsLoader) getSupportLoaderManager().initLoader(LOADER_ID_ITEM, Bundle.EMPTY, new PostsLoaderCallback());
    }

    @OnClick(R.id.btnStartInput)
    void onStartInputButtonClick() {
        startActivity(new Intent(this, InputActivity.class));
    }

    @OnClick(R.id.btnGetPosts)
    void onGetPostsButtonClick() {
        loader.loadNewData();
    }

    private class PostsLoaderCallback implements LoaderManager.LoaderCallbacks<List<Post>> {

        @Override
        public Loader<List<Post>> onCreateLoader(int id, Bundle args) {
            return new PostsLoader(getBaseContext());
        }

        @Override
        public void onLoadFinished(Loader<List<Post>> loader, List<Post> data) {
            DBHelper.bulkInsert(getBaseContext(), data);
            adapter.swapData(data);
        }

        @Override
        public void onLoaderReset(Loader<List<Post>> loader) {
            adapter.swapData(new ArrayList<Post>());
        }
    }

}
