package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.*;
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
    @CollectionTable(name = "crewMember_Roles", joinColumns = @JoinColumn(name = "crewMemberName"))
    @OrderColumn
    @Enumerated(EnumType.STRING)
    private Set<CrewMemberRole> roles;
}
