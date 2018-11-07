package ru.gamma_station.dao;

import ru.gamma_station.domain.Post;

import java.util.List;

public interface PostDAO {
    Post findPost(Long postId);

    void createPost(Post post);
    void deletePost(Post post);

    List<Post> getAllPosts();
}
