package ru.gamma_station.domain.website;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "website_admins")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Admin implements UserDetails {
    @Id
    @Column(nullable = false)
    @NotEmpty
    private String username;

    @Column(nullable = false, length = 60)
    @NotEmpty
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(targetEntity = AdminAuthority.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "username", orphanRemoval = true)

    private Set<AdminAuthority> authorities;

    public Admin(String username, String password, Set<AdminAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
