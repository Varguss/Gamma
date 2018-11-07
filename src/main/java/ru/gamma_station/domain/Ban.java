package ru.gamma_station.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Ban {
    private Timestamp banTime, expirationTime;
    private String reason, ckey, adminCkey, job, adminsWasOnline;
    private int durationTime;

    public boolean isPermamentBan() {
        return durationTime == -1;
    }

    public boolean isJobBan() {
        return !job.isEmpty();
    }

    public boolean isBan() {
        return job.isEmpty();
    }

    public Ban() {
        this.durationTime = -1;
        this.job = "";
    }
}