package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CrewMember {
    @Id
    private String name;

    @ElementCollection(targetClass = CrewMemberRole.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<CrewMemberRole> roles;
}
