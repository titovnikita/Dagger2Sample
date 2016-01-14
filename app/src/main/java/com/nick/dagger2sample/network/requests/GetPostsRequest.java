package com.nick.dagger2sample.network.requests;

import com.nick.dagger2sample.database.models.Post;
import com.nick.dagger2sample.network.Api;

import java.io.IOException;
import java.util.List;

import retrofit.Response;

public class GetPostsRequest extends BaseRequest {
    public static final String REQUEST_TYPE = "get_posts_request";

    @Override
    public String getRequestType() {
        return REQUEST_TYPE;
    }

    @Override
    public Response execute(Api api) {
        Response<List<Post>> response;
        try {
            response = api.getPosts().execute();
        } catch (IOException exception) {
            response = null;
            log(exception.toString());
        }
        return response;
    }
}
