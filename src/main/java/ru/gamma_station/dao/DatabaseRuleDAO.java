package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.Rule;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DatabaseRuleDAO implements RuleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rule findRule(String name) {
        return sessionFactory.getCurrentSession().find(Rule.class, name);
    }

    @Override
    public void saveRule(Rule rule) {
        sessionFactory.getCurrentSession().saveOrUpdate(rule);
    }

    @Override
    public void deleteRule(Rule rule) {
        sessionFactory.getCurrentSession().delete(rule);
    }

    @Override
    public List<Rule> getAllRules() {
        return sessionFactory.getCurrentSession().createQuery("FROM Rule", Rule.class).getResultList();
    }
}