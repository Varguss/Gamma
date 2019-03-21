package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.CrewMember;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DatabaseCrewMemberDAO implements CrewMemberDAO {
    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CrewMember find(String name) {
        return entityManager.find(CrewMember.class, name);
    }

    @Override
    public void save(CrewMember crewMember) {
        entityManager.merge(crewMember);
    }

    @Override
    public void delete(CrewMember crewMember) {
        entityManager.remove(crewMember);
    }

    @Override
    public List<CrewMember> findAll() {
        return entityManager.createQuery("FROM CrewMember", CrewMember.class).getResultList();
    }
}