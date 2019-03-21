package ru.gamma_station.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import ru.gamma_station.dao.AdminDAO;
import ru.gamma_station.domain.website.Admin;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;
import ru.gamma_station.service.AdminService;
import ru.gamma_station.service.DatabaseUserDetailsService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DatabaseUserDetailsService detailsService;
    private AdminService adminService;

    @Autowired
    public void setDetailsService(DatabaseUserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Autowired
    public void setAdminDAO(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                    authorizeRequests()
                .antMatchers("/admin").authenticated()
                .antMatchers("/admin/addAdmin", "/admin/removeAdmin", "/admin/removeAuthority", "/admin/addAuthority")
                .hasAuthority("STAFF_MANAGEMENT")
                .antMatchers("/admin/save", "/admin/removePost", "/admin/edit")
                .hasAuthority("POSTS_MANAGEMENT")
                .antMatchers("/admin/addRule", "/admin/removeRule", "/admin/editRule")
                .hasAuthority("RULES_MANAGEMENT")
                .antMatchers("/admin/addCrew", "/admin/removeCrew", "/admin/addCrewRole", "/admin/removeCrewRole")
                .hasAuthority("CREW_MANAGEMENT")
                .antMatchers("/admin/ban")
                .hasAuthority("BAN_MANAGEMENT")
                    .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login").permitAll()
                    .and().logout()
                .logoutSuccessUrl("/").permitAll()
                    .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());

        if (adminService.getAdmins().size() == 0) {
            auth.authenticationProvider(inMemoryAuthenticationProvider());
        }
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider inMemoryAuthenticationProvider() {
        DaoAuthenticationProvider inMemoryProvider
                = new DaoAuthenticationProvider();
        inMemoryProvider.setPasswordEncoder(passwordEncoder());
        inMemoryProvider.setUserDetailsService(inMemoryUserDetailsManager());
        return inMemoryProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return detailsService;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Admin admin = new Admin("root", passwordEncoder().encode("zxcasdqwe"),
                new HashSet<>(Collections.singletonList(new AdminAuthority("root", Authority.STAFF_MANAGEMENT.name()))));

        return new InMemoryUserDetailsManager(admin);
    }
}