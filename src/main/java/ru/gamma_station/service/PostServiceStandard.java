package ru.gamma_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.PostDAO;
import ru.gamma_station.domain.Post;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceStandard implements PostService {
    private PostDAO dao;

    @Autowired
    public PostServiceStandard(PostDAO dao) {
        this.dao = dao;
    }

    @Override
    public void editPost(Long postId, String newContent) {
        dao.findPost(postId).setContent(validateContent(newContent));
    }

    @Override
    public void editDiscordPost(Long discordMessageId, String content) {
        Post post = findDiscordPost(discordMessageId);
        if (post != null)
            post.setContent(validateContent(content));
    }

    @Override
    public void addPost(String postAuthor, String postContent) {
        dao.createPost(new Post(postAuthor, validateContent(postContent), new Date()));
    }

    @Override
    public void addDiscordPost(String author, String content, Long discordMessageId) {
        dao.createPost(new Post(author, validateContent(content), new Date(), discordMessageId));
    }

    @Override
    public void deletePost(Long postId) {
        Post post = dao.findPost(postId);

        if (post != null)
            dao.deletePost(post);
    }

    @Override
    public void deleteDiscordPost(Long discordMessageId) {
        Post post = findDiscordPost(discordMessageId);
        if (post != null)
            dao.deletePost(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return dao.getAllPosts();
    }

    @Override
    @Transactional(readOnly = true)
    public Post findDiscordPost(Long discordMessageId) {
        List<Post> posts = getAllPosts();

        for (Post post : posts) {
            if (post.getDiscordMessageId() != null && post.getDiscordMessageId().equals(discordMessageId))
                return post;
        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Post findPost(Long id) {
        return dao.findPost(id);
    }

    private String validateContent(String content) {
        return content.replace("\n", "<br/>");
    }
}
