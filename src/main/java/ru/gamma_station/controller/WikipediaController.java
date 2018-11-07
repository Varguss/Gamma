package ru.gamma_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gamma_station.domain.website.AdminAuthority;
import ru.gamma_station.domain.website.Authority;
import ru.gamma_station.service.AdminService;

import java.util.Arrays;
import java.util.HashSet;

@Controller
@RequestMapping("/wiki")
public class WikipediaController {
    @GetMapping
    public String wiki() {

        return "forward:/resources/views/wikipedia.html";
    }
}