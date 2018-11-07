package ru.gamma_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gamma_station.domain.website.Admin;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdmin(username);

        if (admin == null)
            throw new UsernameNotFoundException("User " + username + " has not been found.");

        return admin;
    }
}