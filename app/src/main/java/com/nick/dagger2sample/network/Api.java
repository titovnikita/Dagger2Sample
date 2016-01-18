package com.nick.dagger2sample.network;

import com.nick.dagger2sample.database.models.Post;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface Api {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
