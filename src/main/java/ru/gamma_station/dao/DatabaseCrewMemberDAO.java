package ru.gamma_station.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.gamma_station.domain.CrewMember;

import java.util.List;

@Repository
public class DatabaseCrewMemberDAO implements CrewMemberDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CrewMember findCrewMember(String name) {
        return sessionFactory.getCurrentSession().find(CrewMember.class, name);
    }

    @Override
    public void saveCrewMember(CrewMember crewMember) {
        sessionFactory.getCurrentSession().saveOrUpdate(crewMember);
    }

    @Override
    public void deleteCrewMember(CrewMember crewMember) {
        sessionFactory.getCurrentSession().delete(crewMember);
    }

    @Override
    public List<CrewMember> getAllCrewMembers() {
        return sessionFactory.getCurrentSession().createQuery("FROM CrewMember", CrewMember.class).getResultList();
    }
}