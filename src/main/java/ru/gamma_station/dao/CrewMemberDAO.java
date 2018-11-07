package ru.gamma_station.dao;

import ru.gamma_station.domain.CrewMember;

import java.util.List;

public interface CrewMemberDAO {
    CrewMember findCrewMember(String name);

    void saveCrewMember(CrewMember crewMember);
    void deleteCrewMember(CrewMember crewMember);

    List<CrewMember> getAllCrewMembers();
}
