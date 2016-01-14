package com.nick.dagger2sample.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import com.nick.dagger2sample.R;
import com.nick.dagger2sample.core.DaggerApplication;
import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.network.requests.RequestExecutor;
import com.nick.dagger2sample.network.requests.GetPostsRequest;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Response;

public class MainActivity extends BaseActivity implements RequestExecutor.OnRequestExecuted {

    @Bind(R.id.btnGetPosts)
    Button btnGetPosts;
    @Inject
    RequestExecutor requestExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerApplication.getNetworkComponent(this).inject(this);
    }

    @OnClick(R.id.btnGetPosts)
    void onGetPostsButtonClick() {
        requestExecutor.execute(new GetPostsRequest(), this);
    }

    @Override
    public void onSuccess(String requestType, Response response) {
        switch (requestType) {
            case GetPostsRequest.REQUEST_TYPE:
                List<Post> posts = (List<Post>) response.body();
                makeToast(posts.size());
                break;
        }
    }

    @Override
    public void onFailure(String requestType, int code) {
        switch (requestType) {
            case GetPostsRequest.REQUEST_TYPE:
                makeToast("Error");
                break;
        }
    }
}
