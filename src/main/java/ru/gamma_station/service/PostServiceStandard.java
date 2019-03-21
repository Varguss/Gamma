package ru.gamma_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.PostRepository;
import ru.gamma_station.domain.Post;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceStandard implements PostService {
    private PostRepository repository;

    @Autowired
    public PostServiceStandard(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void edit(Long postId, String newContent) {
        repository.findById(postId).ifPresent(post1 -> post1.setContent(validateContent(newContent)));
    }

    @Override
    public void editDiscordPost(Long discordMessageId, String content) {
        Post post = findDiscordPost(discordMessageId);
        if (post != null)
            post.setContent(validateContent(content));
    }

    @Override
    public void save(String postAuthor, String postContent) {
        repository.save(new Post(postAuthor, validateContent(postContent), new Date()));
    }

    @Override
    public void saveDiscordPost(String author, String content, Long discordMessageId) {
        repository.save(new Post(author, validateContent(content), new Date(), discordMessageId));
    }

    @Override
    public void delete(Long postId) {
        repository.findById(postId).ifPresent(repository::delete);
    }

    @Override
    public void deleteDiscordPost(Long discordMessageId) {
        Post post = findDiscordPost(discordMessageId);
        if (post != null)
            repository.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Post findDiscordPost(Long discordMessageId) {
        List<Post> posts = getAll();

        for (Post post : posts) {
            if (post.getDiscordMessageId() != null && post.getDiscordMessageId().equals(discordMessageId))
                return post;
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Post find(Long id) {
        return repository.findById(id).orElse(null);
    }

    private String validateContent(String content) {
        return content.replace("\n", "<br/>");
    }
}
