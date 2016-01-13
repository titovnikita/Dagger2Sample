package com.nick.dagger2sample.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.network.Api;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btnGetPosts)
    Button btnGetPosts;
    @Inject
    Api networkApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerApplication.getNetworkComponent(this).inject(this);
    }

    @OnClick(R.id.btnGetPosts)
    void onGetPostsButtonClick() {
        networkApi.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Response<List<Post>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    makeToast(response.body().size());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
