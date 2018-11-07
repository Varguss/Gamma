package ru.gamma_station.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServerStatus {
    private boolean isOnline;
    private String name, map, mode, host;
    private int privatePlayersCount;
    private List<Player> playersOnline;

    public ServerStatus(String name, String map, String mode, String host, int privatePlayersCount, List<Player> playersOnline) {
        this.name = name;
        this.map = map;
        this.mode = mode;
        this.host = host;
        this.playersOnline = playersOnline;
        this.isOnline = true;
        this.privatePlayersCount = privatePlayersCount;
    }

    public ServerStatus() {
        this.isOnline = false;
    }

    public int getFullPlayersCount() {
        return privatePlayersCount + playersOnline.size();
    }
}