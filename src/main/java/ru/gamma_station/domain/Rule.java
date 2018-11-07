package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Rule implements Serializable {
    @Id
    @NotEmpty
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String description;
}