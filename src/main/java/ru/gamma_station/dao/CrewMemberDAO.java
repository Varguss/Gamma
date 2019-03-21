package ru.gamma_station.dao;

import ru.gamma_station.domain.CrewMember;

import java.util.List;

public interface CrewMemberDAO {
    CrewMember find(String name);

    void save(CrewMember crewMember);
    void delete(CrewMember crewMember);

    List<CrewMember> findAll();
}
