package com.nick.dagger2sample.database.models.response;

import com.nick.dagger2sample.database.models.Post;

import java.util.List;

public class PostsResponse {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
