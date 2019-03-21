package ru.gamma_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.CrewMemberDAO;
import ru.gamma_station.domain.CrewMember;
import ru.gamma_station.domain.CrewMemberRole;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CrewMemberServiceStandard implements CrewMemberService {
    private CrewMemberDAO dao;

    @Autowired
    public void setDao(CrewMemberDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public CrewMember getMember(String name) {
        return dao.find(name);
    }

    @Override
    public void addMember(String name, Set<CrewMemberRole> roles) {
        dao.save(new CrewMember(name, roles));
    }

    @Override
    public void removeMember(String name) {
        CrewMember member = dao.find(name);

        if (member != null)
            dao.delete(member);
    }

    @Override
    public void addRole(String name, CrewMemberRole role) {
        CrewMember member = dao.find(name);

        if (member != null)
            member.getRoles().add(role);
    }

    @Override
    public void removeRole(String name, CrewMemberRole role) {
        CrewMember member = dao.find(name);

        if (member != null)
            member.getRoles().remove(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CrewMember> getMembers() {
        return dao.findAll();
    }
}
