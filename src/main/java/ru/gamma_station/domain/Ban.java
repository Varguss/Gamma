package ru.gamma_station.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Ban {
    protected Timestamp time, expirationTime;
    protected String reason, ckey, adminCkey, job;
    protected int duration;


}
