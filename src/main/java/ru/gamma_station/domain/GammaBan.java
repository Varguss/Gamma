package ru.gamma_station.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GammaBan extends Ban {
    private String adminsWasOnline;

    public boolean isPermamentBan() {
        return duration == -1;
    }

    public boolean isJobBan() {
        return !job.isEmpty();
    }

    public boolean isBan() {
        return job.isEmpty();
    }

    public GammaBan() {
        this.duration = -1;
        this.job = "";
    }
}