package ru.gamma_station.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.domain.ServerStatus;
import ru.gamma_station.util.ServerStatusUtil;

import java.io.IOException;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {
    @GetMapping
    public String monitoring() {
        return "monitoring";
    }

    @ModelAttribute(name = "gammaServer")
    public ServerStatus addGammaServerStatus() {
        try {
            return ServerStatusUtil.getGammaStationServerStatus();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}