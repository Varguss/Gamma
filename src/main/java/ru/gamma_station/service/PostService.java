package ru.gamma_station.service;

import ru.gamma_station.domain.Post;

import java.util.List;

public interface PostService {
    void editPost(Long postId, String newContent);
    void addPost(String postAuthor, String postContent);
    void deletePost(Long postId);
    List<Post> getAllPosts();
}
