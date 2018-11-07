package ru.gamma_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.AdminDAO;
import ru.gamma_station.domain.website.Admin;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AdminServiceStandard implements AdminService {
    private AdminDAO dao;
    private PasswordEncoder encoder;

    @Autowired
    public AdminServiceStandard(AdminDAO dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.encoder = passwordEncoder;
    }

    @Override
    public void addAuthority(String adminName, Authority authority) {
        Admin admin = dao.findAdmin(adminName);

        if (admin != null)
            admin.getAuthorities().add(new AdminAuthority(admin.getUsername(), authority.name()));
    }

    @Override
    public void removeAuthority(String adminName, Authority authority) {
        Admin admin = dao.findAdmin(adminName);

        if (admin != null)
            admin.getAuthorities().remove(new AdminAuthority(admin.getUsername(), authority.name()));
    }

    @Override
    public void addAdmin(String adminName, String password, Set<AdminAuthority> authorities) {
        dao.createAdmin(new Admin(adminName, encoder.encode(password), authorities));
    }

    @Override
    public void removeAdmin(String adminName) {
        Admin admin = dao.findAdmin(adminName);

        if (admin != null)
            dao.deleteAdmin(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public Admin getAdmin(String adminName) {
        return dao.findAdmin(adminName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> getAdmins() {
        return dao.getAllAdmins();
    }
}
