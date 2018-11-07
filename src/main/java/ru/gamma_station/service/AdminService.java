package ru.gamma_station.service;

import ru.gamma_station.domain.website.Admin;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;

import java.util.List;
import java.util.Set;

public interface AdminService {
    void addAuthority(String adminName, Authority authority);
    void removeAuthority(String adminName, Authority authority);

    void addAdmin(String adminName, String password, Set<AdminAuthority> authorities);
    void removeAdmin(String adminName);

    Admin getAdmin(String adminName);
    List<Admin> getAdmins();
}