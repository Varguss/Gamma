package ru.gamma_station.service;

import org.springframework.data.domain.Page;
import ru.gamma_station.domain.Post;

import java.util.List;

public interface PostService {
    void edit(Long id, String content);
    void editDiscordPost(Long discordMessageId, String content);

    void saveDiscordPost(String author, String content, Long discordMessageId);
    void save(String author, String content);

    void delete(Long id);
    void deleteDiscordPost(Long discordMessageId);

    Post findDiscordPost(Long discordMessageId);
    Post find(Long id);

    List<Post> getAll();
    List<Post> getPage(int page, int size);
}
