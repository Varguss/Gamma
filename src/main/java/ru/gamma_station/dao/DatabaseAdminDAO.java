package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.domain.website.Admin;
import java.util.List;

@Repository
public class DatabaseAdminDAO implements AdminDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Admin findAdmin(String adminName) {
        return sessionFactory.getCurrentSession().find(Admin.class, adminName);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        sessionFactory.getCurrentSession().delete(admin);
    }

    @Override
    public void createAdmin(Admin admin) {
        sessionFactory.getCurrentSession().save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return sessionFactory.getCurrentSession().createQuery("FROM Admin", Admin.class).getResultList();
    }
}
