package ru.gamma_station.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gamma_station.domain.ServerStatus;
import ru.gamma_station.util.ServerStatusUtil;

import java.io.IOException;

@Slf4j
@RestController
public class ServerStatusRestController {
    @GetMapping("server/gamma")
    @ResponseStatus(HttpStatus.OK)
    public ServerStatus getGammaStatus() throws IOException {
        return ServerStatusUtil.getGammaStationServerStatus();
    }

    @GetMapping("server/eris")
    @ResponseStatus(HttpStatus.OK)
    public ServerStatus getErisStatus() throws IOException {
        return ServerStatusUtil.getErisStationServerStatus();
    }
}
