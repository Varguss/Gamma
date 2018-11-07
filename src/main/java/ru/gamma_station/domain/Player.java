package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Player implements Serializable {
    @Column(nullable = false)
    private String byond;

    @Id
    private String ckey;
}