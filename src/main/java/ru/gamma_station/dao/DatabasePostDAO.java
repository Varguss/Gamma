package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.domain.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DatabasePostDAO implements PostDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Post findPost(Long postId) {
        return sessionFactory.getCurrentSession().find(Post.class, postId);
    }

    @Override
    public void createPost(Post post) {
        sessionFactory.getCurrentSession().saveOrUpdate(post);
    }

    @Override
    public void deletePost(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return sessionFactory.getCurrentSession().createQuery("SELECT post FROM Post post ORDER BY post.publishedDate DESC", Post.class).getResultList();
    }
}