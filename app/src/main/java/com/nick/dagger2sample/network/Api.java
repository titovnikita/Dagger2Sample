package com.nick.dagger2sample.network;

import com.nick.dagger2sample.database.models.Post;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

public interface Api {
    @GET("posts")
    Call<List<Post>> getPosts();
}
