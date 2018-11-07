package ru.gamma_station.service;

import ru.gamma_station.domain.CrewMember;
import ru.gamma_station.domain.CrewMemberRole;

import java.util.List;
import java.util.Set;

public interface CrewMemberService {
    CrewMember getMember(String name);

    void addMember(String name, Set<CrewMemberRole> roles);
    void removeMember(String name);

    void addRole(String name, CrewMemberRole role);
    void removeRole(String name, CrewMemberRole role);

    List<CrewMember> getMembers();
}
