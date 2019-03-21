package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.domain.website.Admin;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DatabaseAdminDAO implements AdminDAO {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Admin findAdmin(String adminName) {
        return entityManager.find(Admin.class, adminName);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        entityManager.remove(admin);
    }

    @Override
    public void createAdmin(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return entityManager.createQuery("FROM Admin", Admin.class).getResultList();
    }
}
