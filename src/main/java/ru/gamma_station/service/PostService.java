package ru.gamma_station.service;

import ru.gamma_station.domain.Post;

import java.util.List;

public interface PostService {
    void editPost(Long id, String content);
    void editDiscordPost(Long discordMessageId, String content);

    void addDiscordPost(String author, String content, Long discordMessageId);
    void addPost(String author, String content);

    void deletePost(Long id);
    void deleteDiscordPost(Long discordMessageId);

    Post findDiscordPost(Long discordMessageId);
    Post findPost(Long id);

    List<Post> getAllPosts();
}
