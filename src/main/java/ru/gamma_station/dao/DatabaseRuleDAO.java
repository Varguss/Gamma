package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.Rule;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DatabaseRuleDAO implements RuleDAO {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Rule find(String name) {
        return entityManager.find(Rule.class, name);
    }

    @Override
    public void save(Rule rule) {
        entityManager.merge(rule);
    }

    @Override
    public void delete(Rule rule) {
        entityManager.remove(rule);
    }

    @Override
    public List<Rule> getAll() {
        return entityManager.createQuery("FROM Rule", Rule.class).getResultList();
    }
}