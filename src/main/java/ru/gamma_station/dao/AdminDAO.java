package ru.gamma_station.dao;

import ru.gamma_station.domain.website.Admin;

import java.util.List;
import java.util.Set;

public interface AdminDAO {
//    UserEntity findUser(String userName);
//
//    void deleteUser(String userName);
//
//    void updateUser(String userName, String password, Set<UserRole> userRoles);
//
//    void createUser(String userName, String password, Set<UserRole> userRoles);
    Admin findAdmin(String adminName);

    void deleteAdmin(Admin admin);
    void createAdmin(Admin admin);

    List<Admin> getAllAdmins();
}