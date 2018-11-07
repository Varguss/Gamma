package ru.gamma_station.domain.website;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "website_admins_authorities")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AdminAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String authority;

    public AdminAuthority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
