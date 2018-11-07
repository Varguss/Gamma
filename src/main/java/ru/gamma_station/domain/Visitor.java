package ru.gamma_station.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    private String ipAddress, lastHost;
    private Timestamp lastVisit;
    private Long visits;

    public Visitor(String ipAddress, String lastHost, Timestamp lastVisit) {
        this.ipAddress = ipAddress;
        this.lastHost = lastHost;
        this.lastVisit = lastVisit;
    }

    public Visitor(String ipAddress, String lastHost, Timestamp lastVisit, long visits) {
        this.ipAddress = ipAddress;
        this.lastHost = lastHost;
        this.lastVisit = lastVisit;
        this.visits = visits;
    }
}