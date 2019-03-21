package ru.gamma_station.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player implements Serializable {
    @Column(nullable = false)
    private String byond;

    @Id
    private String ckey;
}